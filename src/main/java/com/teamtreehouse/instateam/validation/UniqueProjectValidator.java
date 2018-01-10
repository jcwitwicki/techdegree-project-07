package com.teamtreehouse.instateam.validation;

import com.teamtreehouse.instateam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProjectValidator implements ConstraintValidator<UniqueProjectConstraint, String> {

    @Autowired
    private ProjectService projectService;

    @Autowired
    public UniqueProjectValidator(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void initialize(UniqueProjectConstraint projectAdded) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name != null && !projectService.findByName(name).isPresent();
    }

}