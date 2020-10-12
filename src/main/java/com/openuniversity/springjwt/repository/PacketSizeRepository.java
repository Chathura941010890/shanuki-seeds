package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.PacketSizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacketSizeRepository extends JpaRepository<PacketSizes, Long> {
    PacketSizes findById (long id);

    PacketSizes getAllById (long id);

    PacketSizes findBySizeName (String name);

    PacketSizes findByPacketWeight(String weight);

    Boolean existsByPacketWeight(String weight);


    @Query(value = "SELECT packet_sizes.id FROM public.packet_sizes", nativeQuery = true)
    List <Long> getAllIds();
}
