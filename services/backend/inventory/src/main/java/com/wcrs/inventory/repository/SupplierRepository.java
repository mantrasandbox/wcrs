package com.wcrs.inventory.repository;

import com.wcrs.inventory.model.Supplier;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    Optional<Supplier> findSupplierByEmail(String email);
}
