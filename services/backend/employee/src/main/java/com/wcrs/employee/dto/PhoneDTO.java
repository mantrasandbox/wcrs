package com.wcrs.employee.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {

    @NotBlank(message = "Country code is required")
    private String countryCode;
    @NotBlank(message = "Phone number is required")
    private String number;
    @NotBlank(message = "Phone category is required")
    private String phoneCategory;

}
