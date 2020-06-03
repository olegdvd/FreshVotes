package com.freshvotes.security;

import com.freshvotes.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class CustomSecurityUser extends User implements UserDetails {
    private static final long serialVersionUID = -6017149916834463524L;

    public CustomSecurityUser() {
        //empty
    }

    public CustomSecurityUser(User user) {
        this.setUsername(getUsername());
        this.setAuthorities(user.getAuthorities());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setId(user.getId());
    }

    @Override
    public Set<Authority> getAuthorities() {
        return this.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
