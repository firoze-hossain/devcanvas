package com.roze.devcanvas.service.interfaces;


import com.roze.devcanvas.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String role_user);
}
