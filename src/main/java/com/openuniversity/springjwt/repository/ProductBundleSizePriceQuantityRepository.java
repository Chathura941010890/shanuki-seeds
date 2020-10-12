package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.Bundle;
import com.openuniversity.springjwt.models.ProductDetails;
import com.openuniversity.springjwt.models.ProductBundleSizesPricesQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBundleSizePriceQuantityRepository extends JpaRepository<ProductBundleSizesPricesQuantity, Long> {

    ProductBundleSizesPricesQuantity findById(long id);

    List <ProductBundleSizesPricesQuantity> findByBundle(Bundle bundle);

    List <ProductBundleSizesPricesQuantity> findByProductDetails(ProductDetails productDetails);

    List <ProductBundleSizesPricesQuantity> findByBundlePrice(long price);

    ProductBundleSizesPricesQuantity findByProductDetailsAndBundle(ProductDetails productDetails, Bundle bundle);

    Boolean existsByProductDetailsAndBundle(ProductDetails productDetails, Bundle packetSizes);

    boolean existsByProductDetails(ProductDetails productDetails);
}
