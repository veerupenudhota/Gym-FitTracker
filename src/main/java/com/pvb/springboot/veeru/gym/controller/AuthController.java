package com.pvb.springboot.veeru.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvb.springboot.veeru.gym.dto.LoginRequest;
import com.pvb.springboot.veeru.gym.dto.RegisterRequest;
import com.pvb.springboot.veeru.gym.model.User;
import com.pvb.springboot.veeru.gym.repository.UserRepository;
import com.pvb.springboot.veeru.gym.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("Username cannot be null");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // default role

        userRepository.save(user);  // ✅ Correct instance call, not static
        return ResponseEntity.ok("User registered successfully");
    }



    // Login endpoint for user login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // Call UserService to authenticate the user
        return userService.login(request);
    }
}
