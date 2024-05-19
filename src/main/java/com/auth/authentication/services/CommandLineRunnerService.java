package com.auth.authentication.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.auth.authentication.dao.AuthorityDAO;
import com.auth.authentication.dao.UsersDAO;
import com.auth.authentication.entity.Authority;
import com.auth.authentication.entity.Users;

@Service
public class CommandLineRunnerService implements CommandLineRunner {

    private final UsersDAO usersDAO;
    private final AuthorityDAO authorityDAO;

    public CommandLineRunnerService(final UsersDAO usersDAO, final AuthorityDAO authorityDAO) {
        this.usersDAO = usersDAO;
        this.authorityDAO = authorityDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] authorities = {
            "ROLE_USER",
            "ROLE_ADMIN"
        };

        for (String authorityName: authorities) {
            if (authorityDAO.exists(authorityName)) {
                continue;
            }

            Authority authority = new Authority();
            authority.setRole(authorityName);
            authorityDAO.save(authority);
        }

        Object[][] users = {
            { "Cristian", "Moreno", 27, "admin", "admin", "ROLE_ADMIN" },
            { "Michael", "Jackson", 99, "user", "user", "ROLE_USER" }
        };

        for (Object[] usr: users) {
            if (usersDAO.exists((String) usr[3])) {
                continue;
            }

            Authority authority = authorityDAO.findbyRole((String) usr[5]);

            Users user = new Users();
            
            user.setName((String) usr[0]);
            user.setLastname((String) usr[1]);
            user.setAge((int) usr[2]);
            user.setUsername((String) usr[3]);
            user.setPassword((String) usr[4]);
            user.setAuthority(authority);

            usersDAO.save(user);
        }
    }
    
}
