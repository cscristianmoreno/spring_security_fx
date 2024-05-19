package com.auth.authentication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth.authentication.entity.Authority;
import com.auth.authentication.models.IAuthorityRepository;
import com.auth.authentication.repository.AuthorityRepository;

@Service
public class AuthorityDAO implements IAuthorityRepository {

    private final AuthorityRepository authorityRepository;

    public AuthorityDAO(final AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void save(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public List<Authority> findAll() {
        return (List<Authority>) authorityRepository.findAll();
    }

    @Override
    public Authority findbyRole(String authority) {
        return find(authority).orElseThrow();
    }

    @Override
    public boolean exists(String name) {
        return find(name).isPresent();
    }

    @Override
    public Optional<Authority> find(String name) {
        Optional<Authority> authority = authorityRepository.findByRole(name);
        return authority;
    }
    
}
