package com.multitenant.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    public JwtAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
