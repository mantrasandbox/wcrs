package com.wcrs.inventory.service;

import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.exception.*;
import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.mapper.InventoryMapper;
import com.wcrs.inventory.model.Material;
import com.wcrs.inventory.model.Supplier;
import com.wcrs.inventory.repository.MaterialRepository;
import com.wcrs.inventory.repository.SupplierRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {
    private final MaterialRepository materialRepository;
    private final SupplierRepository supplierRepository;
    private final InventoryMapper inventoryMapper;


    public SupplierResponseDTO addSupplier(@Valid SupplierRequestDTO supplierRequestDTO) {
        // check if supplier exists in db

        if (supplierRepository.existsSupplierByEmail(supplierRequestDTO.email())){
            throw new DuplicateEmailException("Supplier already exists");
        }

        if(supplierRequestDTO.supplierCategory().equalsIgnoreCase(("Company"))){
            // check for tin
            if(supplierRepository.existsSupplierByTin(supplierRequestDTO.tin())){
                throw new DuplicateTinException("Supplier with this tin already exists");
            }
        }

        if(supplierRequestDTO.supplierCategory().equalsIgnoreCase(("Individual"))){
            // check for nin
            if(supplierRepository.existsSupplierByNin(supplierRequestDTO.nin())){
                throw new DuplicateNinException("Supplier with this nin already exists");
            }
        }

        Supplier savedSupplier = supplierRepository.save(inventoryMapper.toSupplier(supplierRequestDTO));
        return inventoryMapper.toSupplierResponseDTO(savedSupplier);


    }

    public MaterialResponseDTO createMaterial(@Valid MaterialRequestDTO materialRequestDTO) {
        if (materialRequestDTO != null) {
         Material savedMaterial =  materialRepository.saveAndFlush(inventoryMapper.toMaterial(materialRequestDTO));
         return inventoryMapper.toMaterialResponseDTO(savedMaterial);
        }
        return null;
    }

    public List<SupplierResponseDTO> getAllSuppliers(int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));

        Page<Supplier> suppliers = supplierRepository.findAll(pageRequest);

        return suppliers.stream()
                .map(inventoryMapper::toSupplierResponseDTO)
                .toList();
    }

    public SupplierResponseDTO findSupplierByNin(String nin) {
        Supplier supplier = supplierRepository
                .findSupplierByNin(nin)
                .orElseThrow(() -> new SupplierNotFoundException("The Supplier with this nin does not exist"));
        return inventoryMapper.toSupplierResponseDTO(supplier);
    }


    public SupplierResponseDTO findSupplierByTin(Integer tin) {
        Supplier supplier = supplierRepository
                .findSupplierByTin(tin)
                .orElseThrow(() -> new SupplierNotFoundException("The Supplier with this tin does not exist"));
        return inventoryMapper.toSupplierResponseDTO(supplier);
    }

    public SupplierResponseDTO findSupplierByEmail(String email) {
        Supplier supplier = supplierRepository
                .findSupplierByEmail(email)
                .orElseThrow(() -> new SupplierNotFoundException("The Supplier with this email does not exist"));
        return inventoryMapper.toSupplierResponseDTO(supplier);
    }

    public void removeByNin(String nin) {
        // check if email exists
       if (!supplierRepository.existsSupplierByNin(nin)){
           throw new SupplierNotFoundException("The Supplier with this email does not exist");
       }
       supplierRepository.deleteSupplierByNin(nin);
    }

    public void removeByTin(Integer tin) {
        Supplier supplier = supplierRepository.findSupplierByTin(tin)
                .orElseThrow(() -> new SupplierNotFoundException("The supplier with this tin does not exist"));

        supplierRepository.delete(supplier);


    }

    public SupplierResponseDTO updateSupplier(SupplierRequestDTO supplierRequestDTO, String phoneContact) {
        Supplier supplier = supplierRepository.findSupplierByPhoneContact(phoneContact)
                .orElseThrow(() -> new SupplierNotFoundException("The supplier with this phone contact does not exist"));

        inventoryMapper.updateSupplierFromDto(supplierRequestDTO, supplier);
        supplierRepository.save(supplier);
        return inventoryMapper.toSupplierResponseDTO(supplier);
    }

    public List<MaterialResponseDTO> getAllMaterials(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));

        Page<Material> materials = materialRepository.findAll(pageRequest);

        return materials.stream()
                .map(inventoryMapper::toMaterialResponseDTO)
                .toList();
    }

    public MaterialResponseDTO findMaterialByName(String name) {
        // check if material with the name exists
        Material material = materialRepository
                .findMaterialByName(name)
                .orElseThrow(() -> new MaterialNotFoundException("The Material with this name does not exist"));
        return inventoryMapper.toMaterialResponseDTO(material);
    }

    public void removeByName(String name) {
        Material material = materialRepository
                .findMaterialByName(name)
                .orElseThrow(() -> new MaterialNotFoundException("The material with this name does not exist"));

        materialRepository.delete(material);
    }

    public MaterialResponseDTO updateMaterial(MaterialRequestDTO materialRequestDTO, String name) {

        Material material = materialRepository.findMaterialByName(name)
                .orElseThrow(() -> new MaterialNotFoundException("The material with this name does not exist"));

        material.setTotalCost();
        inventoryMapper.updateMaterialFromDto(materialRequestDTO, material);
        return inventoryMapper.toMaterialResponseDTO(material);
    }
}
