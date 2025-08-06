package com.roze.devcanvas.repository;

import com.roze.devcanvas.entity.DevTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevToolRepository extends JpaRepository<DevTool, Integer> {
}