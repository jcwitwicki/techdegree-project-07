package com.teamtreehouse.instateam.web;

import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueRoleValidator implements ConstraintValidator<UniqueRoleConstraint, String> {

    @Autowired
    private RoleService roleService;

    @Override
    public void initialize(UniqueRoleConstraint uniqueRole) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !roleService.findByName(name).isPresent();
//          return name != null;
    }

}
