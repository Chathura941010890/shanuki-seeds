package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.Company;
import com.openuniversity.springjwt.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Controller
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/getDetails")
    @PreAuthorize("hasRole('ADMIN' || 'OPERATOR')")
    public ResponseEntity<?> getDetails(){
        Company company = companyRepository.findById(1);
        return new ResponseEntity <Company>(company, HttpStatus.OK);
    }


}
