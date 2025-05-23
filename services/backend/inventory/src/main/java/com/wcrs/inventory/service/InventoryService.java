package com.wcrs.inventory.service;

import com.wcrs.inventory.repository.MaterialRepository;
import com.wcrs.inventory.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryService {
    private final MaterialRepository materialRepository;
    private final SupplierRepository supplierRepository;
}
