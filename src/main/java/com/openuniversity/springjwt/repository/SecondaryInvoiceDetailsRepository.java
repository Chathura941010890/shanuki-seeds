package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.BasicInvoiceDetails;
import com.openuniversity.springjwt.models.SecondaryInvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondaryInvoiceDetailsRepository extends JpaRepository<SecondaryInvoiceDetails, Long> {
    SecondaryInvoiceDetails findById(long id);

   List<SecondaryInvoiceDetails> findByBasicInvoiceDetails(BasicInvoiceDetails basicInvoiceDetails);

    boolean existsByBasicInvoiceDetails(BasicInvoiceDetails basicInvoiceDetails);

}
