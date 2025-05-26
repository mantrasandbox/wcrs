package com.wcrs.inventory.dto;


import com.wcrs.inventory.enums.SupplierCategory;
import com.wcrs.inventory.validator.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


public record SupplierRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotBlank(message = "Address cannot be blank")
        String address,

        @NotBlank(message = "PhoneContact cannot be blank")
        String phoneContact,

        @Email(message = "This should be a proper email")
        String email,

        @Size(max = 14,message = "Valid nin is required")
        @Pattern(regexp = "^[A-Za-z0-9]{14}$", message = "Nin must be alpha numeric and 14 characters long")
        String nin,

        @Max(value = 10, message = "Valid tin is required" )
        @Min(value = 10, message = "Tin should not be less than 10 digits")
        Integer tin,

        @ValidEnum(enumClass = SupplierCategory.class, message = "Supplier Category is not valid")
        String supplierCategory,

        String website,
        String logo,

        @Valid
        MaterialRequestDTO material
) {
}
