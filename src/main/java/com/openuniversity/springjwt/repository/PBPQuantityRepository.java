package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.PBPQuantity;
import com.openuniversity.springjwt.models.PPPQuantity;
import com.openuniversity.springjwt.models.ProductBundleSizesPricesQuantity;
import com.openuniversity.springjwt.models.ProductPacketSizesPriceQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PBPQuantityRepository extends JpaRepository<PBPQuantity, Long> {
    PBPQuantity findById(long id);

    PBPQuantity findByBundleQuantity(long quantity);

    PBPQuantity findByProductBundleSizesPricesQuantity(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity);

    boolean existsByBundleQuantity(long qua);

    boolean existsByProductBundleSizesPricesQuantity(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity);
}
