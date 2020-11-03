package com.gzhtdq.daas.security.domai;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetail extends User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return getAccountExpired() <= 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getAccountLocked() <= 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getCredentialsExpired() <= 0;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled() > 0;
    }
}
