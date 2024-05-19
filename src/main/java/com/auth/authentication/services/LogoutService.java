package com.auth.authentication.services;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class LogoutService {
    
    public static void logout() {
        try {
            SecurityContextHolder.clearContext();
            SceneService.changeScene("login");
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }
}
