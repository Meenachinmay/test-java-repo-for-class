package org.polarmeet.api.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.polarmeet.api.entities.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "averylongandsecurekeyforjwtauthenticationinourdemoproject12345";


    public String generateToken (User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());

        return createToken(claims, user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // extract a username from a given token
    public String extractUsername(String token) {
       return extractClaim(token, Claims::getSubject);
    }

    //
    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getEmail())) && !isTokenExpired(token);
    }

    //
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //
    private Claims extractAllClaims(String token) {
       return Jwts.parserBuilder()
               .setSigningKey(getSigningKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
    }

    private Key getSigningKey() {
       byte[] keyBytes = SECRET_KEY.getBytes();
       return Keys.hmacShaKeyFor(keyBytes);
    }
}

// In next lecture let's add auth middleware. == Apply Auth middleware

// create a user
// login for a user
// we have the token

// we need to use that token for private routes.
// as of now we have public routes. so we need to create a private route, then make it protective route which
// can be access only with a valid token which we get when we login.'

// For the private routes, auth middleware will be intercepting every incoming request, and will be checking for the auth token  (valid one)