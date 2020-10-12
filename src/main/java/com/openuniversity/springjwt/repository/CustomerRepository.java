package com.openuniversity.springjwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openuniversity.springjwt.models.CustomerDetails;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Long>{

    Optional <CustomerDetails> findCustomerDetailsById(Long id);

    CustomerDetails findCustomerDetailsByCustomerCode(String code);

    CustomerDetails findCustomerDetailsByCustomerName(String name);

    CustomerDetails findCustomerDetailsByCustomerAddress(String address);

    Boolean existsByCustomerCode(String code);

    Boolean existsByCustomer_name(String name);

    Boolean existsByCustomerName(String name);

    long count();
}
