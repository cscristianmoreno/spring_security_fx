package com.auth.authentication.security;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.authentication.dao.UsersDAO;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersDAO usersDAO;

    public CustomUserDetailsService(@Lazy final UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersDAO.loadUserByUsername(username);
    }
    
}
