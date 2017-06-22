package com.autotrek.instantauto.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Joe C. McPherson
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationDto extends AbstractDto {

    @JsonProperty("token")
    public String token;
}
