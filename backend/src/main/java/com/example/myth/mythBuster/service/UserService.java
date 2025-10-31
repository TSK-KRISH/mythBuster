package com.example.myth.mythBuster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myth.mythBuster.model.User;
import com.example.myth.mythBuster.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public boolean validateLogin(String email, String password) {
        User existing = userRepository.findByEmail(email);
        if (existing == null) return false;
        if (!existing.getPassword().equals(password)) return false;
        return true;
    }
}
