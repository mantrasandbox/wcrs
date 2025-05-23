package com.wcrs.inventory.dto;


import java.util.List;

public record SupplierResponseDTO(

        String name,

        String description,

        String address,

        String phoneContact,

        String email,

        String website,

        List<MaterialRequestDTO> material
) {
}
