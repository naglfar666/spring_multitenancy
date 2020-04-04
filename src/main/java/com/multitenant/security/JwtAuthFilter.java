package com.multitenant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String HEADER = "TOKEN";
    private static final String BEARER_TOKEN = "Bearer";
    private static final String JWT_ISSUER= "ducat-springboot-jwttoken";
    private static final String JWT_SECRET_KEY= "asd!@sfSc3@dcdefw";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader(HEADER);

        try {
            SecurityContext context = SecurityContextHolder.getContext();

            if(authenticationHeader != null && authenticationHeader.startsWith(BEARER_TOKEN)) {

                final String bearerTkn = authenticationHeader.replaceAll(BEARER_TOKEN, "");

                try {
                    Jws<Claims> claims = Jwts.parser().requireIssuer(JWT_ISSUER).setSigningKey(JWT_SECRET_KEY).parseClaimsJws(bearerTkn);

                    String powers = (String) claims.getBody().get("powers");
                    Integer id = (Integer) claims.getBody().get("userId");

                    List<GrantedAuthority> authority = new ArrayList<>();

                    for (String role: powers.split(","))
                        authority.add(new SimpleGrantedAuthority(role));

                    JwtAuthToken authenticationTkn = new JwtAuthToken(id, null, authority);

                    context.setAuthentication(authenticationTkn);
                } catch (Exception e) {
                    throw new ServletException("Invalid token.");
                }
            }

            filterChain.doFilter(request, response);
            context.setAuthentication(null);
        } catch(AuthenticationException ex) {
            throw new ServletException("Authentication exception.");
        }
    }
}
