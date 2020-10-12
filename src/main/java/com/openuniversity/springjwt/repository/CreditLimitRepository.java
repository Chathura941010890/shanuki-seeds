package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.CreditLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLimitRepository extends JpaRepository<CreditLimit, Long> {
    CreditLimit findById(long id);

}
