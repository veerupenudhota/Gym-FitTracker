package com.pvb.springboot.veeru.gym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.pvb.springboot.veeru.gym.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF if using stateless auth or custom login
            .authorizeRequests()
                // Allow the following resources without authentication
                .requestMatchers(
                    "/login",
                    "/register",
                    "/index.html",  // Include any static HTML page here
                    "/static/**",
                    "/images1/**",
                    "/music/**",
                    "/css/**",
                    "/js/**",
                    "/favicon.ico",
                    "/manifest.json",
                    "/logo192.png",
                    "/logo512.png",
                    "/robots.txt",
                    "/api/auth/login",
                    "/api/auth/register"
                ).permitAll()
                // Any other request requires authentication
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")  // Custom login page
                .defaultSuccessUrl("/", true)  // Redirect after successful login
                .failureHandler(authenticationFailureHandler())  // Custom failure handler
                .permitAll()  // Allow everyone to access the login page
            .and()
            .logout()
                .logoutUrl("/logout")  // Custom logout URL
                .logoutSuccessUrl("/login?logout=true")  // Redirect after successful logout
                .permitAll();

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService)
                   .passwordEncoder(passwordEncoder())  // Use the password encoder
                   .and()
                   .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt password encoder
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error=true");  // Redirect with error query
    }
}
