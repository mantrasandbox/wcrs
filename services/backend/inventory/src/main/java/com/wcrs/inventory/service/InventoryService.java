package com.wcrs.inventory.service;

import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.model.Material;
import com.wcrs.inventory.repository.MaterialRepository;
import com.wcrs.inventory.repository.SupplierRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryService {
    private final MaterialRepository materialRepository;
    private final SupplierRepository supplierRepository;

    public MaterialResponseDTO createMaterial(@Valid MaterialRequestDTO materialRequestDTO) {
        if (materialRequestDTO != null) {
         //Material savedMaterial =  materialRepository.saveAndFlush(materialRequestDTO)
        }
        return null;
    }
}
