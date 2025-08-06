package com.roze.devcanvas.service.interfaces;



import com.roze.devcanvas.entity.Education;

import java.util.List;

public interface EducationService {
    List<Education> getAllEducationDetails();

    Education findById(int theId);

    Education save(Education theEducation);

    void deleteById(int theId);
}
