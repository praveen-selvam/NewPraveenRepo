package com.autotrek.instantauto.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author praveens
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto extends AbstractDto {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("rolename")
    public String roleName;
    @JsonProperty("customer")
    public String customer;
    @JsonProperty("description")
    public String description;
}
