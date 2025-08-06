package com.roze.devcanvas.service.interfaces;


import com.roze.devcanvas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserName(String username);
}
