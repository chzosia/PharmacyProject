package org.example.pharmacy.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.pharmacy.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.token.validity}")
    private long TOKEN_VALIDITY;

    @Value("${security.token.secret}")
    private  String SECRET_KEY;

    //TODO: Use login DTO as input

    public String createToken(UserEntity user) {
        //subject can be id or username or email or anything else
        long now = System.currentTimeMillis();

       var token = Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", "USER")
                .claim("id", user.getId())
                .issuedAt(new Date(now))
                .expiration(new Date(now+TOKEN_VALIDITY))
                .signWith(generateKey())
                .compact(); //the recommended algorithm is HmacSHA512
       return token;
    }

    public boolean verifyToken(String token) {
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).after(new Date());
    }

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
