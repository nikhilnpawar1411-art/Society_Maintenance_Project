package com.example.societyMaintenanceMgmt.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(Long userId, Long societyId, String role, String loginId,String userName) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("societyId", societyId)
                .claim("role", role)
                .claim("loginId",loginId)
                .claim("userName",userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token){
          try {
            extractClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException  e){
            System.out.println("Exception At JwtUtil-validateToken : "+ e.getMessage());
            return false;
        }
    }

    public Long extractUserId(String token){
//       Claims claims=extractClaims(token);
       return Long.valueOf(extractClaims(token).getSubject());
    }

    public Long extractSocietyId(String token){
        return extractClaims(token).get("societyId", Long.class);
    }

    public String extractRole(String token){
        return extractClaims(token).get("role", String.class);
    }
    public String extractLoginId(String token){
        return extractClaims(token).get("loginId", String.class);
    }
    public String extractUserName(String token){
        return extractClaims(token).get("userName", String.class);
    }

}
