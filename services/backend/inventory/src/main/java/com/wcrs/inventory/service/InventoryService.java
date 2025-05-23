package com.wcrs.inventory.service;

import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.exception.SupplierNotFoundException;
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

    public SupplierResponseDTO addSupplier(@Valid SupplierRequestDTO supplierRequestDTO) {
        // check if supplier exists in db
        supplierRepository.
                findSupplierByEmail(supplierRequestDTO.email())
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
        return null;
    }
}
