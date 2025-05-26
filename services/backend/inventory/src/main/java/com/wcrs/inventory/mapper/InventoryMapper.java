package com.wcrs.inventory.mapper;


import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.enums.SupplierCategory;
import com.wcrs.inventory.model.Material;
import com.wcrs.inventory.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InventoryMapper {

    Material toMaterial(MaterialRequestDTO materialRequestDTO);

    MaterialRequestDTO toMaterialRequestDTO(Material material);

    MaterialResponseDTO toMaterialResponseDTO(Material material);

    @Mapping(target = "supplierCategory", source = "supplierCategory", qualifiedByName = "stringToSupplierCategory")
    @Mapping(target = "material", source = "material", qualifiedByName = "wrapMaterialInList")
    Supplier toSupplier(SupplierRequestDTO supplierRequestDTO);


    @Mapping(target = "material", source = "material", qualifiedByName = "toMaterialDtoList")
    @Mapping(target = "supplierCategory", source = "supplierCategory", qualifiedByName = "supplierCategoryToString")
    SupplierResponseDTO toSupplierResponseDTO(Supplier supplier);


    @Named("stringToSupplierCategory")
    default SupplierCategory stringToSupplierCategory(String supplierCategory) {
        if (supplierCategory == null || supplierCategory.isEmpty())
            return null;
        try {
            return SupplierCategory.valueOf(supplierCategory.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Supplier Category is not valid");
        }
    }

    @Named("wrapMaterialInList")
    default List<Material> wrapMaterialInList(MaterialRequestDTO materialRequestDTO) {
        if (materialRequestDTO == null) return Collections.emptyList();
        return List.of(toMaterial(materialRequestDTO));
    }

    @Named("toMaterialDtoList")
    default List<MaterialResponseDTO> toMaterialDtoList(List<Material> materials) {
        if (materials == null) return Collections.emptyList();
        return materials.stream().map(this::toMaterialResponseDTO).toList();
    }

    @Named("supplierCategoryToString")
    default String supplierCategoryToString(SupplierCategory supplierCategory) {
        return supplierCategory != null? supplierCategory.toString():null;
    }
}
