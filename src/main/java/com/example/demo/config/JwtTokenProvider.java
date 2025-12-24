// package com.example.demo.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.JwtException;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class JwtTokenProvider {

//     private String secretKey = "secret";
//     private int validityInMs = 3600000; // 1 hour

//     // ✅ REQUIRED BY SPRING
//     public JwtTokenProvider() {
//     }

//     // ✅ REQUIRED BY TEST FILE
//     public JwtTokenProvider(String secretKey, int validityInMs) {
//         this.secretKey = secretKey;
//         this.validityInMs = validityInMs;
//     }

//     // ================= TOKEN CREATION =================

//     public String generateToken(Long userId, String email, String role) {

//         Map<String, Object> claims = new HashMap<>();
//         claims.put("userId", userId);
//         claims.put("role", role);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(email)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
//                 .signWith(SignatureAlgorithm.HS256, secretKey)
//                 .compact();
//     }

//     // ================= REQUIRED BY FILTER =================

//     // ✅ USED BY JwtAuthenticationFilter
//     public boolean validateToken(String token) {
//         try {
//             getClaims(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException e) {
//             return false;
//         }
//     }

//     // ✅ USED BY JwtAuthenticationFilter
//     public Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secretKey)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     // ================= OPTIONAL HELPERS =================

//     public String getEmailFromToken(String token) {
//         return getClaims(token).getSubject();
//     }
// }
package com.example.demo.config;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "very-secret-key";
    private final long expirationMillis = 3600000;

    public String generateToken(Long userId, String email, String role) {

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // ✅ SUBJECT = userId
                .claim("email", email)
                .claim("role", role) // ✅ role claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
