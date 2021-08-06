package com.accio.spring.starter.exceptions;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class ValidationErrorHelper {
    private ValidationErrorHelper() {

    }

    /**
     * To create ValidationError from ObjectError.
     *
     * @param objError The ObjectError instance
     * @return ValidationError
     */
    public static ValidationError createValidationError(
            final ObjectError objError
    ) {
        return new ValidationError(
                objError.getObjectName(),
                objError.getDefaultMessage()
        );
    }

    /**
     * To create ValidationError from ConstraintViolation.
     *
     * @param cv The ConstraintViolation instance
     * @return ValidationError
     */
    public static ValidationError createValidationError(
            final ConstraintViolation<?> cv
    ) {
        return new ValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage()
        );
    }

    /**
     * To get a List of ValidationError from List of ObjectErrors.
     *
     * @param objErrorList The list of ObjectError
     * @return List<ValidationError>
     */
    public static List<ValidationError> getValidationErrorsFromObjectErrors(
            final List<ObjectError> objErrorList
    ) {
        List<ValidationError> validationErrors = new ArrayList<>();
        objErrorList.forEach(
                (ObjectError objError) ->
                        validationErrors.add(
                                ValidationErrorHelper
                                        .createValidationError(objError)
                        )
        );
        return validationErrors;
    }

    /**
     * To get a List of ValidationError from List of FieldError.
     *
     * @param fieldErrorList List of FieldError
     * @return List<ValidationError>
     */
    public static List<ValidationError> getValidationErrorsFromFieldErrors(
            final List<FieldError> fieldErrorList
    ) {
        List<ValidationError> validationErrors = new ArrayList<>();
        fieldErrorList.forEach(
                (ObjectError objError) -> validationErrors.add(
                        ValidationErrorHelper.createValidationError(objError)
                )
        );
        return validationErrors;
    }

    /**
     * To get a List of ValidationError from Set of ConstraintViolation<?>.
     *
     * @param constraintViolations Set of ConstraintViolation
     * @return List<ValidationError>
     */
    public static List<ValidationError>
    getValidationErrorsFromConstraintViolation(
            final Set<ConstraintViolation<?>> constraintViolations
    ) {
        List<ValidationError> validationErrors = new ArrayList<>();
        constraintViolations.forEach(
                (ConstraintViolation<?> cv) ->
                        validationErrors.add(
                                ValidationErrorHelper.createValidationError(cv)
                        )
        );
        return validationErrors;
    }

}
