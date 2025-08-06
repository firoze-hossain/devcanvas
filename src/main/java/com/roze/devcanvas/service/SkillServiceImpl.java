package com.roze.devcanvas.service;


import com.roze.devcanvas.entity.Skill;
import com.roze.devcanvas.repository.SkillRepository;
import com.roze.devcanvas.service.interfaces.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {
    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public List<Skill> getSkillsByCategory(String category) {
        return skillRepository.findByCategory(category);
    }

    @Override
    public Skill findById(int theId) {
        Optional<Skill> tempSkill = skillRepository.findById(theId);

        if(tempSkill.isPresent())
            return tempSkill.get();
        else
            throw new RuntimeException("Skill doesn't exist in database");
    }

    @Transactional
    @Override
    public Skill save(Skill theSkill) {
        return skillRepository.save(theSkill);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        skillRepository.deleteById(theId);
    }
}
