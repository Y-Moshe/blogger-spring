package com.blogger.bloggerspring.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogger.bloggerspring.Dtos.AuthCredentialsDto;
import com.blogger.bloggerspring.Dtos.AuthResponseDto;
import com.blogger.bloggerspring.Entities.User;
import com.blogger.bloggerspring.Errors.ApiError;
import com.blogger.bloggerspring.Mappers.UserMapper;
import com.blogger.bloggerspring.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository _repository;
    private final PasswordEncoder _passwordEncoder;
    private final TokenService _tokenService;
    private final AuthenticationManager _authManager;

    @Override
    public User getUserById(Long id) {
        return _repository
                .findById(id)
                .orElseThrow(
                        () -> new ApiError(
                                "User not found",
                                HttpStatus.NOT_FOUND));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return _repository.findByEmail(email);
    }

    @Override
    public AuthResponseDto createUser(User user) {
        String hashedPassword = _passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = _repository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", savedUser.getId());
        claims.put("email", savedUser.getEmail());

        Map<String, Object> tokenMap = _tokenService.createToken(user.getEmail(), claims);
        return AuthResponseDto.builder()
                .user(UserMapper.MAPPER.mapToUserDto(savedUser))
                .token((String) tokenMap.get("token"))
                .expirationDate((Date) tokenMap.get("expiration"))
                .build();
    }

    @Override
    public AuthResponseDto authenticateUser(AuthCredentialsDto credentials) {
        try {

            var authToken = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword());

            // Will throw error incase of mismatch
            _authManager.authenticate(authToken);
            User user = this.getUserByEmail(credentials.getEmail()).get();

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("email", user.getEmail());

            Map<String, Object> tokenMap = _tokenService.createToken(user.getEmail(), claims);
            return AuthResponseDto.builder()
                    .user(UserMapper.MAPPER.mapToUserDto(user))
                    .token((String) tokenMap.get("token"))
                    .expirationDate((Date) tokenMap.get("expiration"))
                    .build();

        } catch (AuthenticationException ex) {
            throw new ApiError(
                    "email or password are incorrect",
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User getLoggedInUser() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return this.getUserByEmail(email).get();
    }
}
