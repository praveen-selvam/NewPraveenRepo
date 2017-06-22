package com.autotrek.instantauto.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author praveens
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DealerDto extends AbstractDto {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("dealername")
    public String dealerName;
    @JsonProperty("contactname")
    public String contactName;
    @JsonProperty("contactemailid")
    public String contactEmailId;
    @JsonProperty("contactmobile")
    public String contactMobile;
    @JsonProperty("designation")
    public String designation;
    @JsonProperty("iqamano")
    public String iqamaNo;
    @JsonProperty("dealerimagepath")
    public String dealerImagePath;
    @JsonProperty("addressline1")
    public String addressLine1;
    @JsonProperty("addressline2")
    public String addressLine2;
    @JsonProperty("addressline3")
    public String addressLine3;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("country")
    public String country;
    @JsonProperty("postalcode")
    public String postalCode;
}
