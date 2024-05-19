package com.auth.authentication.models;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.auth.authentication.entity.Users;

public interface IUserRepository extends IRepository<Users>, UserDetailsService {
    Users findByUsername(String username);

    void authentication(String username, String password);
}
