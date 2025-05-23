package com.wcrs.inventory.controller;

import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/material/create")
    public void createMaterial(){}

    @PostMapping("/supplier/create")
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid SupplierRequestDTO supplierRequestDTO){
        return ResponseEntity.ok(inventoryService.addSupplier(supplierRequestDTO));
    }
}
