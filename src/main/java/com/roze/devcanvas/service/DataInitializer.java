package com.roze.devcanvas.service;


import com.roze.devcanvas.entity.Role;
import com.roze.devcanvas.entity.User;
import com.roze.devcanvas.service.interfaces.RoleRepository;
import com.roze.devcanvas.service.interfaces.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        // Ensure ROLE_USER exists
        Role roleUser = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setId(1); // You can remove this if using @GeneratedValue
                    r.setName("ROLE_ADMIN");
                    return roleRepository.save(r);
                });

        // Ensure default user exists
        String username = "firoze28";
        if (userRepository.findByUserName(username).isEmpty()) {
            User user = new User();
           user.setId(1); // Remove if using @GeneratedValue
            user.setUserName(username);
            user.setPassword(passwordEncoder.encode("firoze28"));
            user.setEnabled(true);

            Set<Role> roles = new HashSet<>();
            roles.add(roleUser);

            user.setRoles(roles);

            userRepository.save(user);

            System.out.println("✅ Created default user: " + username);
        } else {
            System.out.println("ℹ️ Default user already exists: " + username);
        }
    }
}
