package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.ProductDetails;
import com.openuniversity.springjwt.models.ProductSupplierQuantity;
import com.openuniversity.springjwt.models.SupplierDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSupplierQuantityRepository extends JpaRepository<ProductSupplierQuantity, Long> {
    ProductSupplierQuantity findById(long id);

    ProductSupplierQuantity findByProductDetails(ProductDetails productDetails);

    ProductSupplierQuantity findBySupplierDetails(SupplierDetails supplierDetails);

    ProductSupplierQuantity findBySupplierDetailsAndProductDetails(SupplierDetails supplierDetails, ProductDetails productDetails);

    ProductSupplierQuantity findByAdded(boolean added);

    boolean existsByProductDetails(ProductDetails productDetails);

    boolean existsBySupplierDetails(SupplierDetails supplierDetails);

    boolean existsBySupplierDetailsAndProductDetails(SupplierDetails supplierDetails, ProductDetails productDetails);

    boolean existsByAdded(boolean added);
}
