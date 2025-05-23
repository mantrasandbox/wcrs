package com.wcrs.inventory.service;

import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.exception.SupplierNotFoundException;
import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.mapper.InventoryMapper;
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
    private final InventoryMapper inventoryMapper;


    public SupplierResponseDTO addSupplier(@Valid SupplierRequestDTO supplierRequestDTO) {
        // check if supplier exists in db
        supplierRepository.
                findSupplierByEmail(supplierRequestDTO.email())
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
        return null;
    }

    public MaterialResponseDTO createMaterial(@Valid MaterialRequestDTO materialRequestDTO) {
        if (materialRequestDTO != null) {
         Material savedMaterial =  materialRepository.saveAndFlush(inventoryMapper.toMaterial(materialRequestDTO));
         return inventoryMapper.toMaterialResponseDTO(savedMaterial);
        }
        return null;
    }
}
