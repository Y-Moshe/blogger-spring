package com.blogger.bloggerspring.Security.Filters;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtAuthorizationFilter extends OncePerRequestFilter {
public class JwtAuthorizationFilter {

    // @Override
    // protected void doFilterInternal(
    //         HttpServletRequest request,
    //         HttpServletResponse response,
    //         FilterChain filterChain)
    //         throws ServletException, IOException {
    //     final String authHeader = request.getHeader("Authorization");
    //     final String jwtToken = authHeader.substring(7);
    // }
}
