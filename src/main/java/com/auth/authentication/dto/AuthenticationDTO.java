package com.auth.authentication.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    private String name;
    private String lastname;
    private int age;
    private String type;
    private String principal;
    private Object credentials;
    private boolean authenticated;
    private String details;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDateTime sessionDate = LocalDateTime.now();
}
