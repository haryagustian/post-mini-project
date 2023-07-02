package org.hary.agustian.implement;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JsonWebTokenImplement {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsTFunction);
    String generateToken(UserDetails userDetails);
    String generateToken(UserDetails userDetails, Map<String, Object> stringObjectMap);
    String generateRefreshToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
