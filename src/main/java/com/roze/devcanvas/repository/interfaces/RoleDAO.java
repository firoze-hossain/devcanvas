package com.roze.devcanvas.repository.interfaces;


import com.roze.devcanvas.entity.Role;

public interface RoleDAO {
    public Role findRoleByName(String roleName);
}
