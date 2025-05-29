package com.wcrs.inventory.repository;

import com.wcrs.inventory.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {
    boolean existsMaterialByName(String name);

    Optional<Material> findMaterialByName(String name);
}
