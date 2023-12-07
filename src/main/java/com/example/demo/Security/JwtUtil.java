package com.example.demo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private static final long EXPIRATION_TIME = 864_000_000;


    public boolean isValidToken(String token) {
        String username = extractUsername(token);
        Claims claims = extractClaims(token);
        return username != null && validateToken(token, username);
    }

    private String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

  public String generateToken(String username) {
      Date now = new Date();
      Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);
      return Jwts.builder()
              .setSubject(username)
              .setIssuedAt(now)
              .setExpiration(expirationDate)
              .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
              .compact();
  }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, String username) {
        Claims claims = extractClaims(token);
        return username.equals(claims.getSubject()) &&
                !isTokenExpired(claims.getExpiration());
    }

    private boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
