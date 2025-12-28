// package com.example.demo.config;

// import io.jsonwebtoken.Claims;
// import jakarta.servlet.*;
// import jakarta.servlet.http.*;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends GenericFilter {

//     private final JwtTokenProvider jwtTokenProvider;

//     public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {

//         HttpServletRequest req = (HttpServletRequest) request;
//         String header = req.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);

//             if (jwtTokenProvider.validateToken(token)) {
//                 Claims claims = jwtTokenProvider.getClaims(token);

//                 UsernamePasswordAuthenticationToken auth =
//                         new UsernamePasswordAuthenticationToken(
//                                 claims.getSubject(),
//                                 null,
//                                 Collections.emptyList()
//                         );

//                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//                 SecurityContextHolder.getContext().setAuthentication(auth);
//             }
//         }

//         chain.doFilter(request, response);
//     }
// }

package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    // ✅ Spring injects JwtTokenProvider HERE
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUsername(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.emptyList()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
