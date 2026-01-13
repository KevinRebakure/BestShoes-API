package com.rebakure.bestshoes.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    public String generateToken(String email) {
        final long tokenExpiration = 1000 * 60 * 60 * 24; // 1 day
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(Keys.hmacShaKeyFor("Secret".getBytes()))
                .compact();
    }
}
