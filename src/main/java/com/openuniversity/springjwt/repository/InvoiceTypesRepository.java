package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.InvoiceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface InvoiceTypesRepository extends JpaRepository<InvoiceTypes , Long> {
    InvoiceTypes findById(long id);

    InvoiceTypes findByType(String Type);
}
