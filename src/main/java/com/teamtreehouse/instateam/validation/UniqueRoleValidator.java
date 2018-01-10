package com.teamtreehouse.instateam.validation;

import com.teamtreehouse.instateam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoleValidator implements ConstraintValidator<UniqueRoleConstraint, String> {

    @Autowired
    private RoleService roleService;

    @Autowired
    public UniqueRoleValidator(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void initialize(UniqueRoleConstraint roleAdded) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name != null && !roleService.findByName(name).isPresent();
    }

}
