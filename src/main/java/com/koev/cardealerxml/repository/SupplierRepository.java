package com.koev.cardealerxml.repository;

import com.koev.cardealerxml.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("select s from Supplier s where s.isImporter = false")
    Set<Supplier> localSuppliers();
}
