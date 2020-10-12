package com.openuniversity.springjwt.repository;

import com.openuniversity.springjwt.models.EmployeeDetails;
import com.openuniversity.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {
    Optional <EmployeeDetails> findEmployeeDetailsById(long id);

    EmployeeDetails findEmployeeDetailsByEmployeeCode(String code);

    EmployeeDetails findEmployeeDetailsByEmployeeName(String name);

    EmployeeDetails findByUser(User user);

    Boolean existsByEmployeeCode(String code);

    boolean existsByEmployeeName(String name);
}
