package com.blogger.bloggerspring.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blogger.bloggerspring.Services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final TokenService _tokenService;
    private final UserDetailsService _userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Extract the "Authorization" header and check if it follows the "Bearer TOKEN"
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the "Authorization" token and check the token signature (will not
        // verify the expiration)
        final String jwtToken = authHeader.substring(7);
        if (!_tokenService.isTokenValid(jwtToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Will throw exception if invalid token or expired
        final String userEmail = _tokenService.decodeToken(jwtToken).getSubject();

        // If email present and the user is not authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Fetch user from database
            UserDetails userDetails = this._userDetailsService.loadUserByUsername(userEmail);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), // sets the user email as principal
                    null,
                    userDetails.getAuthorities());

            authToken.setDetails(userDetails);
            // Mark user as authenticated by setting the authentication context
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // Authentication succeeded
        filterChain.doFilter(request, response);
    }
}
