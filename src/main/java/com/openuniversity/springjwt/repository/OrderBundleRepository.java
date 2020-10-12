package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.BasicOrderDetails;
import com.openuniversity.springjwt.models.OrderBundle;
import com.openuniversity.springjwt.models.ProductBundleSizesPricesQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBundleRepository extends JpaRepository<OrderBundle, Long> {
    OrderBundle findById(long id);

    List<OrderBundle> findByBasicOrderDetails(BasicOrderDetails basicOrderDetails);

    OrderBundle findByProductBundleSizesPricesQuantities(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity);
}
