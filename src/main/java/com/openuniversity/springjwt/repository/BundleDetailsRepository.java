package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.Bundle;
import com.openuniversity.springjwt.models.PacketSizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BundleDetailsRepository extends JpaRepository<Bundle, Long> {
    Bundle findById (long id);

    Bundle getAllById (long id);

    Bundle findByBundleName (String name);

    Bundle findByPacketQuantity(long quantity);

    Boolean existsByPacketQuantity(long quantity);


    @Query(value = "SELECT bundle_details.id FROM public.bundle_details", nativeQuery = true)
    List <Long> getAllIds();
}
