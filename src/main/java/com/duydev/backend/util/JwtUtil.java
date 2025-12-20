package com.duydev.backend.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.duydev.backend.domain.enums.TypeKey;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.access-key}")
    private String accessKey;
    
    @Value("${jwt.refresh-key}")
    private String refreshKey;

    @Value("${jwt.access-expired}")
    private int accessExpired;

    @Value("${jwt.refresh-expired}")
    private int refreshExpired;

    // Get Keys
    public Key getKey(TypeKey typeKey) {
        if (typeKey == TypeKey.ACESSTOKEN) {
            return Keys.hmacShaKeyFor(Base64.getDecoder().decode(accessKey));
        } else {
            return Keys.hmacShaKeyFor(Base64.getDecoder().decode(refreshKey));
        }
    }

    // Generate Token
    public String generateToken(TypeKey typeKey, List<String> authorities, String subject) {
        // Create component of token
        int timeExpired =  typeKey == TypeKey.ACESSTOKEN ? accessExpired : refreshExpired;
        Key key = getKey(typeKey);
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, Object> claims = new HashMap<>();

        // Set claims
        claims.put("roles", authorities);
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(currentTimeMillis))
            .setExpiration(new Date(currentTimeMillis + timeExpired))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    // Extract Token
    public String extractUsername(String token, TypeKey typeKey){
       return extractClaim(token, typeKey, Claims::getSubject);
    }


    public Date extractExpiration(String token, TypeKey typeKey) {
        return extractClaim(token, typeKey, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token, TypeKey typeKey){
        return extractExpiration(token, typeKey).before(new Date());
    }

    public <T> T extractClaim(String token, TypeKey typeKey, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token, typeKey);
        return claimsResolver.apply(claims);
    }
 
    public Claims extractAllClaims(String token, TypeKey typeKey) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(getKey(typeKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new BadCredentialsException("Token is invalid");
        }

    }
}
