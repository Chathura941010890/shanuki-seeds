package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.PSQPacketQuantity;
import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductSupplierQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PSQPacketQuantityRepository extends JpaRepository<PSQPacketQuantity, Long> {
    PSQPacketQuantity findById(long id);

    PSQPacketQuantity findByPacketSizes(PacketSizes packetSizes);

    List<PSQPacketQuantity> findByProductSupplierQuantity(ProductSupplierQuantity productSupplierQuantity);

    boolean existsByPacketSizes(PacketSizes packetSizes);

    boolean existsByProductSupplierQuantity(ProductSupplierQuantity productSupplierQuantity);
}
