package com.accio.spring.starter.exceptions;

import com.accio.spring.starter.responses.StandardResponse;
import com.accio.spring.starter.responses.StandardResponseBuilder;
import com.accio.spring.starter.responses.StandardResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * StandardResponseBuilder instance to build response objects.
     */
    @Autowired
    private StandardResponseBuilder standardResponseBuilder;

    private StandardResponse<StandardResponseError>
    buildStandardResponseFromException(
            final BaseException e,
            final HttpStatus status
    ) {
        log.error(
                "CODE - [{}] , MESSAGE - {} , STACKTRACE - {}",
                e.getErrorCode(),
                e.getLocalizedMessage(),
                e.getStackTrace()
        );
        return standardResponseBuilder
                .createErrorResponse(
                        status.toString(),
                        status.value(),
                        e.getLocalizedMessage(),
                        e.getErrorCode()
                );
    }

    private StandardResponse<StandardResponseError>
    buildStandardResponseFromException(
            final BaseRuntimeException e,
            final HttpStatus status
    ) {
        log.error(
                "CODE - [{}] , MESSAGE - {} , STACKTRACE - {}",
                e.getErrorCode(),
                e.getLocalizedMessage(),
                e.getStackTrace()
        );
        return standardResponseBuilder
                .createErrorResponse(
                        status.toString(),
                        status.value(),
                        e.getLocalizedMessage(),
                        e.getErrorCode()
                );
    }

    private ResponseEntity<Object> buildResponseEntity(
            final BaseRuntimeException e,
            final HttpStatus status) {
        return new ResponseEntity<>(
                buildStandardResponseFromException(e, status),
                status
        );
    }

    private ResponseEntity<Object> buildResponseEntity(
            final BaseException e,
            final HttpStatus status
    ) {
        return new ResponseEntity<>(
                buildStandardResponseFromException(e, status),
                status
        );
    }

    private ResponseEntity<Object> buildResponseEntity(
            final BaseException e,
            final HttpStatus status,
            final List<Object> subErrors
    ) {
        StandardResponse<StandardResponseError> standardResponse =
                buildStandardResponseFromException(e, status);
        standardResponse.setSubErrors(subErrors);
        return new ResponseEntity<>(standardResponse, status);
    }

    private ResponseEntity<Object> buildResponseEntity(
            final BaseRuntimeException e,
            final HttpStatus status,
            final List<Object> subErrors
    ) {
        StandardResponse<StandardResponseError> standardResponse =
                buildStandardResponseFromException(e, status);
        standardResponse.setSubErrors(subErrors);
        return new ResponseEntity<>(standardResponse, status);
    }

    /*
     ********************* Overriding Exceptions Start*********************
     */

    /**
     * Handle MissingServletRequestParameterException.
     * Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            final MissingServletRequestParameterException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        String errorMessage = ex.getParameterName() + " parameter is missing";
        BadRequestException badRequestException =
                new BadRequestException(errorMessage, ex);
        return buildResponseEntity(badRequestException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle HttpMediaTypeNotSupportedException.
     * This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            final HttpMediaTypeNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(
                " media type is not supported. Supported media types are "
        );
        ex.getSupportedMediaTypes()
                .forEach(t -> builder.append(t)
                        .append(", "));
        Integer errorCode = (Integer) HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
        BaseRuntimeException baseRuntimeException =
                new BaseRuntimeException(
                        builder.substring(0, builder.length() - 2),
                        ex, errorCode.toString()
                );

        return buildResponseEntity(
                baseRuntimeException,
                HttpStatus.UNSUPPORTED_MEDIA_TYPE
        );
    }

    /**
     * Handle MethodArgumentNotValidException.
     * Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException
     *                that is thrown when @Valid validation
     *                fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        BadRequestException badRequestException =
                new BadRequestException("Validation error");

        List<Object> validationErrors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(
                (ObjectError objError) -> validationErrors.add(
                        ValidationErrorHelper.createValidationError(objError)
                )
        );

        ex.getBindingResult().getGlobalErrors().forEach(
                (ObjectError objError) -> validationErrors.add(
                        ValidationErrorHelper.createValidationError(objError)
                )
        );
        return buildResponseEntity(
                badRequestException,
                HttpStatus.BAD_REQUEST,
                validationErrors
        );
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is
     * malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            final HttpMessageNotReadableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info(
                "{} to {}",
                servletWebRequest.getHttpMethod(),
                servletWebRequest.getRequest().getServletPath()
        );
        BadRequestException badRequestException =
                new BadRequestException(
                        "Malformed JSON request", ex
                );
        return buildResponseEntity(
                badRequestException,
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            final HttpMessageNotWritableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        InternalErrorException internalErrorException =
                new InternalErrorException("Error writing JSON output", ex);
        return buildResponseEntity(
                internalErrorException,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param ex      NoHandlerFoundException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            final NoHandlerFoundException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        BadRequestException badRequestException =
                new BadRequestException(
                        String.format(
                                "Could not find the %s method for URL %s",
                                ex.getHttpMethod(),
                                ex.getRequestURL()
                        ),
                        ex);
        /* apiError.setDebugMessage(ex.getMessage()); */
        return buildResponseEntity(badRequestException, HttpStatus.BAD_REQUEST);
    }

    /*
     ********************* Overriding Exceptions End*********************
     */

    /**
     * Handles javax.validation.ConstraintViolationException.
     * Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            final javax.validation.ConstraintViolationException ex
    ) {
        Integer errorCode = (Integer) HttpStatus.BAD_REQUEST.value();

        BadRequestException badRequestException =
                new BadRequestException(
                        "Validation error",
                        ex,
                        errorCode.toString()
                );

        List<Object> validationErrors = new ArrayList<>();

        ex.getConstraintViolations().forEach(
                (ConstraintViolation<?> cv) -> validationErrors.add(
                        ValidationErrorHelper.createValidationError(cv)
                )
        );
        return buildResponseEntity(
                badRequestException,
                HttpStatus.BAD_REQUEST,
                validationErrors
        );
    }


    /**
     * Handle javax.persistence.EntityNotFoundException.
     *
     * @param ex javax.persistence.EntityNotFoundException
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            final javax.persistence.EntityNotFoundException ex
    ) {
        NotFoundException notFoundException =
                new NotFoundException(
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex
                );
        return buildResponseEntity(notFoundException, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for
     * different DB causes.
     *
     * @param ex      DataIntegrityViolationException
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(
            final DataIntegrityViolationException ex,
            final WebRequest request
    ) {
        // INFO: check below commented code
        // commented for future reference
        // it's not good idea to send database error details to client thus
        // returning
        // simple internal server error

        /*
         * if (ex.getCause() instanceof ConstraintViolationException) { return
         * buildResponseEntity(new ApiError(
         * HttpStatus.CONFLICT, "Database error",
         * ex.getCause())); } return buildResponseEntity(new
         * ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
         */

        InternalErrorException internalError = new InternalErrorException(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex
        );
        return buildResponseEntity(
                internalError,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Handle Exception, handle generic Exception.class.
     *
     * @param ex      MethodArgumentTypeMismatchException
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            final MethodArgumentTypeMismatchException ex,
            final WebRequest request
    ) {
        String msg = "The parameter '%s' of value '%s' could not be converted "
                + "to type '%s'";
        BadRequestException badRequestException =
                new BadRequestException(
                        String.format(
                                msg,
                                ex.getName(),
                                ex.getValue(),
                                ex.getRequiredType().getSimpleName()
                        ),
                        ex
                );
        /* apiError.setDebugMessage(ex.getMessage()); */
        return buildResponseEntity(badRequestException, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles BadRequestException.
     * Created to encapsulate errors with more detail than
     * javax.persistence.BadRequestException.
     *
     * @param e BadRequestException
     * @return ResponseEntity<StandardResponse < StandardResponseError>>
     */
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<StandardResponse<StandardResponseError>>
    handleBadRequestException(
            final BadRequestException e
    ) {
        return new ResponseEntity<>(
                buildStandardResponseFromException(
                        e, HttpStatus.BAD_REQUEST
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handles NotFoundException.
     * Created to encapsulate errors with more detail than
     * javax.persistence.NotFoundException.
     *
     * @param e the NotFoundException
     * @return ResponseEntity<StandardResponse < StandardResponseError>>
     */
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<StandardResponse<StandardResponseError>>
    handleNotFoundException(final NotFoundException e) {
        return new ResponseEntity<>(
                buildStandardResponseFromException(
                        e,
                        HttpStatus.NOT_FOUND
                ),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Handles UnauthorizedException. Created to encapsulate errors with more
     * detail than javax.persistence.UnauthorizedException.
     *
     * @param e UnauthorizedException
     * @return ResponseEntity<StandardResponse < StandardResponseError>>
     */
    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<StandardResponse<StandardResponseError>>
    handleUnauthorizedException(
            final UnauthorizedException e
    ) {
        return new ResponseEntity<>(
                buildStandardResponseFromException(
                        e, HttpStatus.UNAUTHORIZED
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    /**
     * Handles InternalErrorException. Created to encapsulate errors with more
     * detail than javax.persistence.InternalErrorException.
     *
     * @param e InternalErrorException
     * @return ResponseEntity<StandardResponse < StandardResponseError>>
     */
    @ExceptionHandler(InternalErrorException.class)
    protected ResponseEntity<StandardResponse<StandardResponseError>>
    handleInternalErrorException(
            final InternalErrorException e
    ) {
        return new ResponseEntity<>(
                buildStandardResponseFromException(
                        e,
                        HttpStatus.INTERNAL_SERVER_ERROR
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Handles InternalErrorException. Created to encapsulate errors with
     * more detail than javax.persistence.InternalErrorException.
     *
     * @param e Exception
     * @return ResponseEntity<StandardResponse < StandardResponseError>>
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<StandardResponse<StandardResponseError>>
    handleException(final Exception e) {
        InternalErrorException internalErrorException =
                new InternalErrorException("Internal server error", e);
        return new ResponseEntity<>(
                buildStandardResponseFromException(
                        internalErrorException,
                        HttpStatus.INTERNAL_SERVER_ERROR
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
