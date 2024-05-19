package com.auth.authentication.dao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.authentication.entity.Users;
import com.auth.authentication.models.IUserRepository;
import com.auth.authentication.repository.UserRepository;
import com.auth.authentication.security.CustomAuthenticationManager;
import com.auth.authentication.security.CustomUserDetails;
import com.auth.authentication.services.ObserverService;

@Service
public class UsersDAO implements IUserRepository {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationManager customAuthenticationManager;
    private final ObserverService observerService;

    public UsersDAO(final UserRepository userRepository, final PasswordEncoder passwordEncoder, 
    @Lazy final CustomAuthenticationManager customAuthenticationManager, final ObserverService observerService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticationManager = customAuthenticationManager;
        this.observerService = observerService;
    }

    @Override
    public void save(Users users) {
        final String password = users.getPassword();
        users.setPassword(passwordEncoder.encode(password));
        userRepository.save(users);
    }

    @Override
    public Users findByUsername(String username) {
        return find(username).orElseThrow();
    }

    @Override
    public List<Users> findAll() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    public boolean exists(String name) {
        return find(name).isPresent();
    }

    @Override
    public Optional<Users> find(String name) {
        Optional<Users> users = userRepository.findByUsername(name);
        return users;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = findByUsername(username);
        return new CustomUserDetails(users);
    }

    @Override
    public void authentication(String username, String password) throws AuthenticationException {
        try {
            Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            customAuthenticationManager.authenticate(usernamePasswordAuthenticationToken);
            observerService.notifyAuthenticationSuccess("LoginController");
        }
        catch (NoSuchElementException | AuthenticationException e) {
            observerService.notifyAuthenticationFailed("LoginController");
        }
    }
}
