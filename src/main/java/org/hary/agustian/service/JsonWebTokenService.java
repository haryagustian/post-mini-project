package org.hary.agustian.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hary.agustian.implement.JsonWebTokenImplement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JsonWebTokenService implements JsonWebTokenImplement {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private String buildToken(long expiration, UserDetails userDetails, Map<String, Object> mapClaims){
        return Jwts
                .builder()
                .setClaims(mapClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public String generateToken(UserDetails userDetails, Map<String, Object> stringObjectMap) {
        return buildToken(jwtExpiration, userDetails, stringObjectMap);
    }
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(refreshExpiration, userDetails, new HashMap<>());
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return (
                extractUsername(token).equals(userDetails.getUsername())
                ) &&
                !isTokenExpired(token);
    }
}
