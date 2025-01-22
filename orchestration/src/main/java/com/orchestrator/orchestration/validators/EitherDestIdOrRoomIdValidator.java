package com.orchestrator.orchestration.validators;

import com.orchestrator.orchestration.annotations.EitherDestIdOrRoomId;
import com.orchestrator.orchestration.objects.vms.NotificationVM;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EitherDestIdOrRoomIdValidator implements ConstraintValidator<EitherDestIdOrRoomId, NotificationVM> {

    @Override
    public void initialize(EitherDestIdOrRoomId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(NotificationVM value, ConstraintValidatorContext context) {
        return value.getDestId() != null && !value.getDestId().isEmpty() ||
                value.getRoomId() != null && !value.getRoomId().isEmpty();
    }

}
