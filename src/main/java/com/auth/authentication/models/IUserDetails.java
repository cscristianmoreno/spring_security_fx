package com.auth.authentication.models;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDetails extends UserDetails {
    String getName();
    
    String getLastname();

    int getAge();

    LocalDateTime getDate();

    String getToken();

    void setToken(String token);
}
