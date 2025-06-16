package org.polarmeet.api.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.polarmeet.api.entities.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    // Security key (SECURITY KEY)
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate a JWT token for a given user
    public String generateToken (User user) {
        Map<String, Object> claims = new HashMap<>();

        // you can add any custom claims you want
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
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