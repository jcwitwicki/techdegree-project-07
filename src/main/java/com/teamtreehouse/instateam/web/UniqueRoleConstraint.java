package com.teamtreehouse.instateam.web;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRoleValidator.class)
public @interface UniqueRoleConstraint {
    String message() default "Role already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
