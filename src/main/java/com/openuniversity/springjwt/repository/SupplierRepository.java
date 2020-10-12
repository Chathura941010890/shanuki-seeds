package com.openuniversity.springjwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openuniversity.springjwt.models.SupplierDetails;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierDetails, Long>{

    Optional <SupplierDetails> findSupplierDetailsById(Long id);

    SupplierDetails findSupplierDetailsBySupplierCode(String code);

    SupplierDetails findSupplierDetailsBySupplierName(String name);

    SupplierDetails findSupplierDetailsBySupplierAddress(String address);

    Boolean existsBySupplierCode(String code);

    Boolean existsBySupplier_name(String name);

    Boolean existsBySupplierName(String name);

    long count();
}
