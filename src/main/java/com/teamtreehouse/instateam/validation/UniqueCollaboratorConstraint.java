package com.teamtreehouse.instateam.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCollaboratorValidator.class)
public @interface UniqueCollaboratorConstraint {

        String message() default "Collaborator already exists";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

    }


