package com.auth.authentication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.authentication.entity.Authority;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Optional<Authority> findByRole(String authority);
}
