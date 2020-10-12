package com.openuniversity.springjwt.repository;


import com.openuniversity.springjwt.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsById(long id);

    Company findById(long id);
}
