package com.auth.authentication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends BaseEntity {
    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String lastname;

    @Column(length = 32)
    private int age;

    @Column(length = 54)
    private String username;

    private String password;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Authority authority;
}
