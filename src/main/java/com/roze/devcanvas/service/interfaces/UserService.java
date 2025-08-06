package com.roze.devcanvas.service.interfaces;


import com.roze.devcanvas.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
}
