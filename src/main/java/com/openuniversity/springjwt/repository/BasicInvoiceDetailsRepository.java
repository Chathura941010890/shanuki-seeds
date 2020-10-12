package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.BasicInvoiceDetails;
import com.openuniversity.springjwt.models.BasicOrderDetails;
import com.openuniversity.springjwt.models.EmployeeDetails;
import com.openuniversity.springjwt.models.InvoiceTypes;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BasicInvoiceDetailsRepository extends JpaRepository<BasicInvoiceDetails, Long> {
    BasicInvoiceDetails findById(long id);

    BasicInvoiceDetails findByBasicOrderDetails(BasicOrderDetails basicOrderDetails);

    BasicInvoiceDetails findByInvoiceTypes(InvoiceTypes invoiceTypes);

    BasicInvoiceDetails findByCreatedDate(Date date);

    boolean existsByBasicOrderDetails(BasicOrderDetails basicOrderDetails);

    boolean existsByInvoiceTypes(InvoiceTypes invoiceTypes);

    boolean existsByCreatedDate(Date date);

    boolean existsByEmployeeDetails(EmployeeDetails employeeDetails);

    List <BasicInvoiceDetails> findByEmployeeDetails(EmployeeDetails employeeDetails);

}
