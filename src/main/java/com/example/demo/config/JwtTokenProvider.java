// package com.example.demo.config;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.util.Date;

// @Component
// public class JwtTokenProvider {
//     private final SecretKey key;
//     private final long validityInMilliseconds;

//     public JwtTokenProvider(String secretKey, long validityInMilliseconds) {
//         this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
//         this.validityInMilliseconds = validityInMilliseconds;
//     }

//     public String generateToken(Long userId, String email, String role) {
//         Date now = new Date();
//         Date validity = new Date(now.getTime() + validityInMilliseconds);

//         return Jwts.builder()
//                 .setSubject(userId.toString())
//                 .claim("email", email)
//                 .claim("role", role)
//                 .setIssuedAt(now)
//                 .setExpiration(validity)
//                 .signWith(key)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException e) {
//             return false;
//         }
//     }

//     public Claims getClaims(String token) {
//         return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//     }
// }

package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final long validityInMilliseconds;

    // ✅ Values injected from application.properties
    public JwtTokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}") long validityInMilliseconds
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String generateToken(Long userId, String email, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
