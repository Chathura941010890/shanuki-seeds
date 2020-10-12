package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.BasicOrderDetails;
import com.openuniversity.springjwt.models.OrderPackets;
import com.openuniversity.springjwt.models.ProductPacketSizesPriceQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPacketsRepository extends JpaRepository<OrderPackets, Long> {
    OrderPackets findById(long id);

    List<OrderPackets> findByBasicOrderDetails(BasicOrderDetails basicOrderDetails);

    OrderPackets findByProductPacketSizesPriceQuantities(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity);
}
