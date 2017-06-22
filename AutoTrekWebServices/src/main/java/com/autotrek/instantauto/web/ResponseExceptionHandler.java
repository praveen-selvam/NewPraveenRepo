package com.autotrek.instantauto.web;

import com.autotrek.instantauto.web.dto.ApiErrorMessage;
import com.autotrek.instantauto.ApiException;
import com.autotrek.instantauto.ApiExceptionBuilder;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is a controller advice that provides exception handling. So this is
 * invoked when the controller raises an exception.
 *
 * @author Joe C. McPherson
 */
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    /**
     * This is a catch all to capture any exceptions we have not handled. This
     * will return a generic ResponseEntity.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorMessage> handleAnyException(Exception ex) {
        LOGGER.error("Service error caught: <{}>", ex.getMessage(), ex);
        return errorResponse(ApiExceptionBuilder.GENERIC_API.build(ex));
    }

    /**
     * This will catch all ApiExceptions. This will return an ResponseEntity
     * based on the exception caught.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiErrorMessage> handleApiException(ApiException ex) {
        return errorResponse(ex);
    }

    /**
     * This treats all data integrity problems and constraint problems as a bad
     * requests. So if the service does not do something nicer, then it will
     * simply be caught here.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({DataIntegrityViolationException.class,
        ConstraintViolationException.class
    })
    public ResponseEntity<ApiErrorMessage> handleIntegrityExceptions(Exception ex) {
        return errorResponse(ApiExceptionBuilder.BAD_REQUEST.build(ex));
    }

    /*
     * Private helper method to build a ResponseEntity.
     */
    private ResponseEntity<ApiErrorMessage> errorResponse(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ApiErrorMessage.fromApiException(ex));
    }
}
