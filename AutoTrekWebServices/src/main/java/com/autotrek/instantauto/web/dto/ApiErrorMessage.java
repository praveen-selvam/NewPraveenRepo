package com.autotrek.instantauto.web.dto;

import com.autotrek.instantauto.ApiException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents an error message from the API.
 *
 * @author Joe C. McPherson
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorMessage extends AbstractDto {
    @JsonProperty("error_code")
    public String code;
    @JsonProperty("error_message")
    public String message;
    @JsonProperty("error_details")
    public String details;

    /**
     * Utility method to build an ErrorMessage object
     * from the supplied ApiException.
     *
     * @param ex The ApiException to build the error message.
     * @return
     */
    public static ApiErrorMessage fromApiException(ApiException ex) {
        ApiErrorMessage ret = new ApiErrorMessage();
        ret.code = ex.getCode();
        ret.message = ex.getMessage();
        ret.details = ex.getDetails();

        return ret;
    }
}
