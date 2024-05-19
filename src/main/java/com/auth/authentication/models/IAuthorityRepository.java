package com.auth.authentication.models;

import com.auth.authentication.entity.Authority;

public interface IAuthorityRepository extends IRepository<Authority> {
    Authority findbyRole(String authority);
}
