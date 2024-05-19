package com.auth.authentication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.authentication.entity.Users;

import jakarta.transaction.Transactional;


@Transactional
@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
