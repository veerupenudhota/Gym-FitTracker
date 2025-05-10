package com.pvb.springboot.veeru.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pvb.springboot.veeru.gym.dto.LoginRequest;
import com.pvb.springboot.veeru.gym.dto.RegisterRequest;
import com.pvb.springboot.veeru.gym.model.User;
import com.pvb.springboot.veeru.gym.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // This will be injected now

    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "User already exists";
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUsername(), encodedPassword, request.getEmail(), "USER");
        userRepository.save(user);
        return "Registration successful";
    }


    // Login method
    public String login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> "Login successful")
                .orElse("Invalid credentials");
    }
}
