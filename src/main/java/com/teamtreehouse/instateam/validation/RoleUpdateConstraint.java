package com.teamtreehouse.instateam.validation;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleUpdateConstraint {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
