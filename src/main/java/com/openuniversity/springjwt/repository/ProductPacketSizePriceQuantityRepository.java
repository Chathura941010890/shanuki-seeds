package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductDetails;
import com.openuniversity.springjwt.models.ProductPacketSizesPriceQuantity;
import com.openuniversity.springjwt.payload.request.ProductDetailsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPacketSizePriceQuantityRepository extends JpaRepository<ProductPacketSizesPriceQuantity, Long> {

    ProductPacketSizesPriceQuantity findById(long id);

    List<ProductPacketSizesPriceQuantity> findByPacketSizes(PacketSizes packetSizes);

    List<ProductPacketSizesPriceQuantity> findByProductDetails(ProductDetails productDetails);

    List<ProductPacketSizesPriceQuantity> findByPacketPrice(long price);

    ProductPacketSizesPriceQuantity findByProductDetailsAndPacketSizes(ProductDetails productDetails, PacketSizes packetSizes);

    Boolean existsByProductDetailsAndPacketSizes(ProductDetails productDetails, PacketSizes packetSizes);

    boolean existsByProductDetails(ProductDetails productDetails);
}
