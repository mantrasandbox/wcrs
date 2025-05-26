package com.wcrs.inventory.controller;

import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.model.Supplier;
import com.wcrs.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/supplier/{nin}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByNin(@PathVariable String nin){
        return ResponseEntity.ok(inventoryService.findSupplierByNin(nin));
    }

    @GetMapping("/supplier/{tin}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByTin(@PathVariable Integer tin){
        return ResponseEntity.ok(inventoryService.findSupplierByTin(tin));
    }

    @GetMapping("/supplier/{email}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByEmail(@PathVariable String email){
        return ResponseEntity.ok(inventoryService.findSupplierByEmail(email));
    }
}
