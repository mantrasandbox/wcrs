package com.wcrs.inventory.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


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

        String website,
        String logo,

        @Valid
        MaterialRequestDTO material
) {
}
