package com.autotrek.instantauto;

import org.springframework.http.HttpStatus;

/**
 * This is a utility class to build all ApiExceptions.
 *
 * It includes both a HTTP Code and a general message to return to the calling
 * client.
 *
 * @author Joe C. McPherson
 */
public enum ApiExceptionBuilder {
    UNAUTHORIZED("api.auth.unauthorized", HttpStatus.UNAUTHORIZED, "Authorization was not provided or is not correct."),
    FORBIDDEN("api.auth.forbidden", HttpStatus.FORBIDDEN, "Access to the supplied resource is forbidden."),
    NOT_FOUND("api.not.found", HttpStatus.NOT_FOUND, "The supplied resource is not found."),
    BAD_REQUEST("api.bad.request", HttpStatus.BAD_REQUEST, "Invalid request, will not process"),
    CONFLICT("api.data.conflict", HttpStatus.CONFLICT, "A conflict exists. Unable to process request."),
    GENERIC_API("api.generic", HttpStatus.INTERNAL_SERVER_ERROR, "Generic API error response");

    private String code;
    private HttpStatus status;
    private String message;

    ApiExceptionBuilder(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ApiException build() {
        return build(null, null);
    }

    public ApiException build(String details) {
        return build(details, null);
    }

    public ApiException build(Throwable cause) {
        return build(null, cause);
    }

    public ApiException build(String details, Throwable cause) {
        return new ApiException(code, status, message, details, cause);
    }

}
