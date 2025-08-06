package com.roze.devcanvas.service;

import com.roze.devcanvas.entity.DevTool;
import com.roze.devcanvas.repository.DevToolRepository;
import com.roze.devcanvas.service.interfaces.DevToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DevToolServiceImpl implements DevToolService {
    private final DevToolRepository devToolRepository;

    @Autowired
    public DevToolServiceImpl(DevToolRepository devToolRepository) {
        this.devToolRepository = devToolRepository;
    }

    @Override
    public List<DevTool> getAllDevTools() {
        return devToolRepository.findAll();
    }

    @Override
    @Transactional
    public DevTool save(DevTool devTool) {
        return devToolRepository.save(devTool);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        devToolRepository.deleteById(id);
    }

    @Override
    public DevTool findById(int id) {
        Optional<DevTool> result = devToolRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Did not find dev tool with id: " + id);
        }
    }
}