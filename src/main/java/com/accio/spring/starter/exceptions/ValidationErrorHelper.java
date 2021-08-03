package com.accio.spring.starter.exceptions;

import com.accio.spring.starter.responses.StandardResponse;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationErrorHelper {

    public static ValidationError createValidationError(ObjectError objError) {
        return new ValidationError(
                objError.getObjectName(),
                objError.getDefaultMessage()
        );
    }

    public static ValidationError createValidationError(ConstraintViolation<?> cv) {
        return new ValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage()
        );
    }

    public static List<ValidationError> getValidationErrorsFromObjectErrors(List<ObjectError> objErrorList) {
        List<ValidationError> validationErrors = new ArrayList<>();
        objErrorList.forEach((ObjectError objError) -> validationErrors.add(
                ValidationErrorHelper.createValidationError(objError)
        ));
        return validationErrors;
    }

    public static List<ValidationError> getValidationErrorsFromFieldErrors(List<FieldError> objErrorList) {
        List<ValidationError> validationErrors = new ArrayList<>();
        objErrorList.forEach((ObjectError objError) -> validationErrors.add(
                ValidationErrorHelper.createValidationError(objError)
        ));
        return validationErrors;
    }


    public static List<ValidationError> addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        List<ValidationError> validationErrors = new ArrayList<>();
        constraintViolations.forEach((ConstraintViolation<?> cv) ->
                    validationErrors.add(
                            ValidationErrorHelper.createValidationError(cv)
                    )
        );
        return validationErrors;
    }



}
