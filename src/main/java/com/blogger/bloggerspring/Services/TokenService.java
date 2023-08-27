package com.blogger.bloggerspring.Services;

import java.util.Map;

import io.jsonwebtoken.Claims;

public interface TokenService {
    Map<String, Object> createToken(String subject, Map<String, Object> claims);

    Claims decodeToken(String token);

    boolean isTokenValid(String token);
}
