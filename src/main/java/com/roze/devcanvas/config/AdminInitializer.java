package com.roze.devcanvas.config;

import com.roze.devcanvas.entity.Role;
import com.roze.devcanvas.entity.User;
import com.roze.devcanvas.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void initAdminUser() {
        String adminUsername = "firoze.hossain";
        String adminEmail = "firoze.hossain@outlook.com";
        String adminPassword = "firoze28";

        // Check if admin DOES NOT exist (correct condition)
        if (!userRepository.existsByUsernameIgnoreCase(adminUsername)) {
            User admin = User.builder()
                    .username(adminUsername)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
            System.out.println("Default admin user created successfully!");
        } else {
            System.out.println("Admin user already exists. Skipping creation.");
        }
    }
}
