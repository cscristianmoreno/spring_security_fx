package com.auth.authentication.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authority extends BaseEntity {
    private String role;
}
