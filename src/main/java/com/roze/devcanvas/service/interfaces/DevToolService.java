package com.roze.devcanvas.service.interfaces;

import com.roze.devcanvas.entity.DevTool;

import java.util.List;

public interface DevToolService {
    List<DevTool> getAllDevTools();

    DevTool save(DevTool devTool);

    void deleteById(Integer id);

    DevTool findById(int id);
}
