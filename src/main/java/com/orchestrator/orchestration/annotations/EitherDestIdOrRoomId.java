package com.orchestrator.orchestration.annotations;

import com.orchestrator.orchestration.validators.EitherDestIdOrRoomIdValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EitherDestIdOrRoomIdValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EitherDestIdOrRoomId {
    String message() default "Either destId or roomId must be provided";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

}
