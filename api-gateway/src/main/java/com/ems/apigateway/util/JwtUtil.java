package com.ems.apigateway.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        if (secret.length() < 32) {
            throw new IllegalArgumentException(
                    "JWT secret must be at least 32 characters long"
            );
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractClaims(String token) {

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT token expired");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("JWT token unsupported");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("JWT token malformed");
        } catch (SignatureException e) {
            throw new RuntimeException("JWT signature invalid");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT token is empty");
        }
    }
}
