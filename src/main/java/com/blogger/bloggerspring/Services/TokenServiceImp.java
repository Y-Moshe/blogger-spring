package com.blogger.bloggerspring.Services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenServiceImp implements TokenService {
    @Value("${app.jwt-secret-key}")
    private String JWT_SECRET_KEY;

    @Value("${app.jwt-expiration}")
    private int JWT_EXPIRATION;

    /**
     * Return a Map with key-value pairs with a "token" of type string and the
     * "expiration" of type date.
     */
    @Override
    public Map<String, Object> createToken(
            String subject,
            Map<String, Object> claims) {

        Date expiration = new Date(System.currentTimeMillis() + this.JWT_EXPIRATION);
        String token = Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("expiration", expiration);
        return result;
    }

    @Override
    public Claims decodeToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenValid(String token) {
        String[] chunks = token.split("\\.");
        if (chunks.length != 3)
            return false;

        String jwtWithoutSignature = chunks[0] + "." + chunks[1];
        String jwtSignature = chunks[2];

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(
                SignatureAlgorithm.HS256,
                getSignInKey(),
                signature -> Decoders.BASE64URL.decode(signature));

        return validator.isValid(jwtWithoutSignature, jwtSignature);
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(this.JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
