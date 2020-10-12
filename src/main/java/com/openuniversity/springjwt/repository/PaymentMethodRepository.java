package com.openuniversity.springjwt.repository;


import com.openuniversity.springjwt.models.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethods, Long> {
    PaymentMethods findById(long id);

    PaymentMethods findByMethod(String method);
}
