package com.verter.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

  private final String secretKey;

  private final long jwtExpirationMinutes;

  public JwtService(@Value("${jwt.secret}") String secretKey,
                    @Value("${jwt.expiration.minutes}") long jwtExpirationMinutes) {
    this.secretKey = secretKey;
    this.jwtExpirationMinutes = jwtExpirationMinutes;
  }

  public String generateToken(String username) {
    return generateToken(new HashMap<>(), username);
  }

  public String generateToken(Map<String, Object> extraClaims, String username) {
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(username)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * jwtExpirationMinutes)))
      .signWith(getSigningKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public boolean isTokenValid(String token, String username) {
    final String usernameInToken = extractUsername(token);
    return username.equals(usernameInToken) && !isTokenExpired(token);
  }

  public String extractUsername(String token) {
    return extractClaims(token, Claims::getSubject);
  }

  public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaims(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSigningKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
