package com.autotrek.instantauto.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A DTO for a user response.
 *
 * @author Joe C. McPherson
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends AbstractDto {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("username")
    public String username;
    @JsonProperty("firstname")
    public String firstname;
    @JsonProperty("lastname")
    public String lastname;
}
