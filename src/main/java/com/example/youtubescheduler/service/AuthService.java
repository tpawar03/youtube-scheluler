package com.example.youtubescheduler.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AuthService {
    private final Map<String, String> users = Map.of(
            "admin", "1234",
            "user", "pass"
    );

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}

