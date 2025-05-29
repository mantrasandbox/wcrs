package com.wcrs.inventory.dto;


import com.wcrs.inventory.dto.validators.CreateSupplierValidationGroup;
import com.wcrs.inventory.enums.SupplierCategory;
import com.wcrs.inventory.validator.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;


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
        @Pattern(regexp = "^[A-Za-z0-9]{14}$", message = "Nin must be alpha numeric and 14 characters long",groups = CreateSupplierValidationGroup.class )
        String nin,

        @Max(value = 10, message = "Valid tin is required" )
        @Min(value = 10, message = "Tin should not be less than 10 digits",groups = CreateSupplierValidationGroup.class )
        @Pattern(regexp = "^[0-9]{10}$", message = "TIN must be an alpha numeric and 10 characters long",groups = CreateSupplierValidationGroup.class )
        Integer tin,

        @ValidEnum(enumClass = SupplierCategory.class, message = "Supplier Category is not valid", groups = CreateSupplierValidationGroup.class )
        String supplierCategory,

        String website,
        String logo,

        @Valid
        @NotEmpty(groups = CreateSupplierValidationGroup.class )
        List<MaterialRequestDTO> material
) {
}
