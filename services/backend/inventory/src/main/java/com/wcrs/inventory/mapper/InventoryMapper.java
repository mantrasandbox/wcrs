package com.wcrs.inventory.mapper;


import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.dto.SupplierRequestDTO;
import com.wcrs.inventory.dto.SupplierResponseDTO;
import com.wcrs.inventory.enums.SupplierCategory;
import com.wcrs.inventory.model.Material;
import com.wcrs.inventory.model.Supplier;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InventoryMapper {

    @Mapping(target = "totalCost", expression = "java(materialRequestDTO.quantity()!=null?new BigDecimal(materialRequestDTO.quantity()).multiply(materialRequestDTO.costPerUnit()):null)")
    Material toMaterial(MaterialRequestDTO materialRequestDTO);

    MaterialRequestDTO toMaterialRequestDTO(Material material);

    @Mapping(target = "totalCost", expression = "java(material.getTotalCost())")
    MaterialResponseDTO toMaterialResponseDTO(Material material);

    @Mapping(target = "supplierCategory", source = "supplierCategory", qualifiedByName = "stringToSupplierCategory")
    @Mapping(target = "material", source = "material", qualifiedByName = "wrapMaterialInList")
    Supplier toSupplier(SupplierRequestDTO supplierRequestDTO);


    @Mapping(target = "material", source = "material", qualifiedByName = "toMaterialDtoList")
    @Mapping(target = "supplierCategory", source = "supplierCategory", qualifiedByName = "supplierCategoryToString")
    SupplierResponseDTO toSupplierResponseDTO(Supplier supplier);


    /**
     *
     * MappingTarget tells MapStruct to modify the existing object instead of creating a new one.

     * NullValuePropertyMappingStrategy.IGNORE prevents overwriting fields with null values (very useful in PATCH-style updates).
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSupplierFromDto(SupplierRequestDTO dto, @MappingTarget Supplier entity);

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
    default List<Material> wrapMaterialInList(List<MaterialRequestDTO> materialRequestDTO) {
        if (materialRequestDTO == null) return Collections.emptyList();
        return materialRequestDTO.stream()
                .map(this::toMaterial)
                .collect(Collectors.toList());
    }

    @Named("toMaterialDtoList")
    default List<MaterialResponseDTO> toMaterialDtoList(List<Material> materials) {
        if (materials == null) return Collections.emptyList();
        return materials.stream().map(this::toMaterialResponseDTO).toList();
    }

    @Named("supplierCategoryToString")
    default String supplierCategoryToString(SupplierCategory supplierCategory) {
        return supplierCategory != null ? supplierCategory.toString() : null;
    }

    /**
     *
     * MappingTarget tells MapStruct to modify the existing object instead of creating a new one.

     * NullValuePropertyMappingStrategy.IGNORE prevents overwriting fields with null values (very useful in PATCH-style updates).
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMaterialFromDto(MaterialRequestDTO dto, @MappingTarget Material entity);

}
