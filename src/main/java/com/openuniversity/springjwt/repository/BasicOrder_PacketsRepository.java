package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.*;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BasicOrder_PacketsRepository extends JpaRepository<BasicOrderDetails, Long> {
    BasicOrderDetails findById(long id);

    List <BasicOrderDetails> findByCustomerDetails(CustomerDetails customerDetails);

    List <BasicOrderDetails> findByEmployeeDetails(EmployeeDetails employeeDetails);

    List <BasicOrderDetails> findByOrderPlacedTypes(OrderPlacedTypes orderPlacedTypes);

    List <BasicOrderDetails> findByOrderPlacedStatus(boolean status);

    List <BasicOrderDetails> findByDeadline(Date date);

    List <BasicOrderDetails> findByInvoiced(boolean invoiced);

    boolean existsByCustomerDetails(CustomerDetails customerDetails);

    boolean existsByEmployeeDetails(EmployeeDetails employeeDetails);

    boolean existsByOrderPlacedTypes(OrderPlacedTypes orderPlacedTypes);

    boolean existsByOrderPlacedStatus(boolean status);

    boolean existsByInvoiced(boolean invoiced);
}
