package com.wcrs.inventory.controller;

import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/material/create")
    public ResponseEntity<MaterialResponseDTO> createMaterial(@RequestBody @Valid MaterialRequestDTO materialRequestDTO){

        return ResponseEntity.ok(inventoryService.createMaterial(materialRequestDTO));
    }

    @PostMapping("/supplier/create")
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid SupplierRequestDTO supplierRequestDTO){
        return ResponseEntity.ok(inventoryService.addSupplier(supplierRequestDTO));
    }

    @GetMapping("/supplier/viewAll")
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers(
            @RequestParam(name = "page",defaultValue = "0", required = false) int page,
            @RequestParam(name = "size",defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(inventoryService.getAllSuppliers(page,size));
    }
}
