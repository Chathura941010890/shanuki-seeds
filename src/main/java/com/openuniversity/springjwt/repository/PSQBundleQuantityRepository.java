package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.Bundle;
import com.openuniversity.springjwt.models.PSQBundleQuantity;
import com.openuniversity.springjwt.models.ProductSupplierQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PSQBundleQuantityRepository  extends JpaRepository<PSQBundleQuantity, Long> {
    PSQBundleQuantity findById(long id);

    List<PSQBundleQuantity> findByProductSupplierQuantity(ProductSupplierQuantity productSupplierQuantity);

    PSQBundleQuantity findByBundle(Bundle bundle);

    boolean existsByProductSupplierQuantity(ProductSupplierQuantity productSupplierQuantity);

    boolean existsByBundle(Bundle bundle);
}
