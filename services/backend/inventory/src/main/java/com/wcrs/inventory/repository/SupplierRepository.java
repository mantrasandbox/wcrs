package com.wcrs.inventory.repository;

import com.wcrs.inventory.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    Optional<Supplier> findSupplierByEmail(String email);

    boolean existsSupplierByEmail( String email);

    boolean existsSupplierByTin(Integer tin);

    boolean existsSupplierByNin(String nin);

    Optional<Supplier> findSupplierByNin(String nin);

    Optional<Supplier> findSupplierByTin(Integer tin);


    void deleteSupplierByNin(String nin);
}


