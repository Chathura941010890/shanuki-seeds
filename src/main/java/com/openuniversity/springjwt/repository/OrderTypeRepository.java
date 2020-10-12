package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.OrderPlacedTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderPlacedTypes, Long> {

    OrderPlacedTypes findById(long id);

    OrderPlacedTypes findByType(String type);

    boolean existsByType(String type);
}
