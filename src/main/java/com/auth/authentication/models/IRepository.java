package com.auth.authentication.models;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    void save(T t);

    List<T> findAll();
    
    boolean exists(String name);
    
    Optional<T> find(String name);
}
