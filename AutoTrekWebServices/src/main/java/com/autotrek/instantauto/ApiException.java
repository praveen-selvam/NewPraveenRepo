package com.autotrek.instantauto;

import org.springframework.http.HttpStatus;

/**
 * All exceptions thrown by the API should use this class.
 *
 * @author Joe C. McPherson
 */
public class ApiException extends RuntimeException {
    private String code;
    private HttpStatus status;
    private String details;

    protected ApiException (String code, HttpStatus status, String message, String details, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.status = status;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
