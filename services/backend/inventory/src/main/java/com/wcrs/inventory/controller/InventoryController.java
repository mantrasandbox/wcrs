package com.wcrs.inventory.controller;

import com.wcrs.inventory.dto.*;
import com.wcrs.inventory.dto.validators.CreateMaterialValidationGroup;
import com.wcrs.inventory.dto.validators.CreateSupplierValidationGroup;
import com.wcrs.inventory.service.InventoryService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/material/create")
    public ResponseEntity<MaterialResponseDTO> createMaterial(@RequestBody @Validated({Default.class, CreateMaterialValidationGroup.class }) MaterialRequestDTO materialRequestDTO){

        return ResponseEntity.ok(inventoryService.createMaterial(materialRequestDTO));
    }

    @GetMapping("/material/viewAll")
    public ResponseEntity<List<MaterialResponseDTO>> getAllMaterials(
            @RequestParam(name = "page",defaultValue = "0", required = false) int page,
            @RequestParam(name = "size",defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(inventoryService.getAllMaterials(page,size));
    }

    @GetMapping("/material/{name}")
    public ResponseEntity<MaterialResponseDTO> getMaterialByName(@PathVariable String name){
        return ResponseEntity.ok(inventoryService.findMaterialByName(name));
    }

    @DeleteMapping("/material/delete/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable("name") String name){
        inventoryService.removeByName(name);
        return ResponseEntity.noContent().build();
    }

    // Update using the name
    @PutMapping("/material/update/{name}")
    public ResponseEntity<MaterialResponseDTO> updateMaterial(@RequestBody @Validated({Default.class}) MaterialRequestDTO materialRequestDTO, @PathVariable("name") String name){
        return ResponseEntity.ok(inventoryService.updateMaterial(materialRequestDTO,name));

    }

    @PostMapping("/supplier/create")
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody @Validated({Default.class, CreateSupplierValidationGroup.class }) SupplierRequestDTO supplierRequestDTO){
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

    @DeleteMapping("/supplier/delete/{nin}")
    public ResponseEntity<Void> deleteByNin(@PathVariable("nin") String nin){
        inventoryService.removeByNin(nin);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supplier/delete/{tin}")
    public ResponseEntity<Void> deleteByTin(@PathVariable("tin") Integer tin){
        inventoryService.removeByTin(tin);
        return ResponseEntity.noContent().build();
    }

    // Update using the phoneContact
    @PutMapping("/supplier/update/{phoneContact}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@RequestBody @Validated({Default.class}) SupplierRequestDTO supplierRequestDTO, @PathVariable("phoneContact") String phoneContact){
        return ResponseEntity.ok(inventoryService.updateSupplier(supplierRequestDTO,phoneContact));

    }
}
