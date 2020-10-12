package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, Long> {
    ProductDetails findById (long id);

    ProductDetails findByProductCode(String code);

    ProductDetails findByProductName(String name);

    Boolean existsByProductCode(String code);

    Boolean existsByProductName(String name);
}
