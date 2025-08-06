package com.roze.devcanvas.repository.interfaces;


import com.roze.devcanvas.entity.User;

public interface UserDAO {
    User findByUserName(String userName);
}
