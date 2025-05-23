package com.wcrs.inventory.dto;

import com.wcrs.inventory.model.Material;


import java.util.List;

public record SupplierRequestDTO(
        String name,
        String description,
        String address,
        String phoneContact,
        String email,
        String website,
        String logo,
        MaterialDTO material
) {
}
