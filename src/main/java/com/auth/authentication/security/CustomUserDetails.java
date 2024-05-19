package com.auth.authentication.security;

import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import com.auth.authentication.entity.Users;
import com.auth.authentication.models.IUserDetails;

import lombok.ToString;

@ToString
public class CustomUserDetails implements IUserDetails {
    private final Users users;
    private String token;

    public CustomUserDetails(final Users users) {
        this.users = users;
    }

    @Override
    public String getName() {
        return users.getName();
    }

    @Override
    public String getLastname() {
        return users.getLastname();
    }

    @Override
    public int getAge() {
        return users.getAge();
    }

    @Override
    public LocalDateTime getDate() {
        return users.getDate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(users.getAuthority().getRole());
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
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

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    
}
