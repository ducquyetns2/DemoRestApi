package com.ducquyet.restapi.service;

import com.ducquyet.restapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final SecretKey key;
    public String generateToken(User user) {
        var now=new Date();
        int EXPIRATION_TIME_HOUR = 1;
        return Jwts.builder().signWith(key)
                  .issuedAt(now)
                  .expiration(DateUtils.addHours(now, EXPIRATION_TIME_HOUR))
                .subject(user.getUsername())
                  .compact();
    }
    public boolean validateToken(String token) {
        Claims claim=extractClaims(token);
        Date date=claim.getExpiration();
        Date now=new Date();
        boolean before = extractClaims(token).getExpiration().after(now);
        return before;
    }
    public String extractData(String token) {
        return extractClaims(token).getSubject();
    }
    private Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(key)
                .build().parseSignedClaims(token).getPayload();
    }
}
