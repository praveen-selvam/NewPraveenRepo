package com.autotrek.instantauto.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Used to pass user credentials.
 *
 * @author Joe C. McPherson
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCredentialsDto extends AbstractDto {

    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;
}
