package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.PPPQuantity;
import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductPacketSizesPriceQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PPPQuantityRepository extends JpaRepository<PPPQuantity, Long> {
    PPPQuantity findById(long id);

    PPPQuantity findByPacketQuantity(long quantity);

    PPPQuantity findByProductPacketSizesPriceQuantity(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity);

    boolean existsByPacketQuantity(long qua);

    boolean existsByProductPacketSizesPriceQuantity(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity);
}
