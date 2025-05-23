package com.wcrs.inventory.mapper;


import com.wcrs.inventory.dto.MaterialRequestDTO;
import com.wcrs.inventory.dto.MaterialResponseDTO;
import com.wcrs.inventory.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InventoryMapper {

    Material toMaterial(MaterialRequestDTO materialRequestDTO);

    MaterialRequestDTO toMaterialRequestDTO(Material material);

    MaterialResponseDTO toMaterialResponseDTO(Material material);
}
