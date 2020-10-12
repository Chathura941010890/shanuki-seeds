package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.SupplierDetails;
import com.openuniversity.springjwt.payload.request.SupplierAddRequest;
import com.openuniversity.springjwt.payload.request.SupplierDetailsRequest;
import com.openuniversity.springjwt.payload.request.SupplierEditRequest;
import com.openuniversity.springjwt.payload.response.SupplierDetailsResponse;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Controller
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    EmailValidation emailValidation;


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllSupplierDetails() {
        boolean suppliersExists = false;
        long count = supplierRepository.count();
        //System.out.println("XXXXXXXXXXXXXXX " + count);
        List <SupplierDetails> list= new ArrayList<SupplierDetails>();
        if (count > 0){
            suppliersExists = true;
            //supplierDetails = new SupplierDetails(supplierCode);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No suppliers have not been registered!"));
        }
        if(suppliersExists){

            for(long i = 1; i<= count; i++) {
                list.add(supplierRepository.findSupplierDetailsById(i).orElse(null));// = supplierRepository.findSupplierDetailsById(i).orElse(null);
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The supplier has not been registered!"));
        }

        return new ResponseEntity<List<SupplierDetails>>(list, HttpStatus.OK);
    }

    @GetMapping("/one/{supplierCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getSupplierDetails(@PathVariable("supplierCode") String supplierCode) {
        boolean supplierExists = false;
        boolean supplierActive = false;

        SupplierDetails supplierDetails = new SupplierDetails();
        if (supplierRepository.existsBySupplierCode(supplierCode)){
            supplierExists = true;
            supplierDetails = supplierRepository.findSupplierDetailsBySupplierCode(supplierCode);
            //supplierDetails = new SupplierDetails(supplierCode);
        } else {
            if(supplierRepository.existsBySupplierName(supplierCode)){
                supplierDetails = supplierRepository.findSupplierDetailsBySupplierName(supplierCode);
            }
            else{
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: The supplier has not been registered!"));

            }
        }

        if(supplierDetails.getSupplierStatus().equals("Active")){
            supplierActive = true;
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The supplier is not activated!"));
        }
        if(supplierExists){
            return ResponseEntity.ok(new SupplierDetailsResponse(
                    supplierDetails.getSupplier_code(),supplierDetails.getSupplier_name(),
                    supplierDetails.getSupplier_address(), supplierDetails.getSupplier_email(),
                    supplierDetails.getSupplier_contact_number()
            ));
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The supplier has not been registered!"));
        }
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierAddRequest supplierAddRequest){
        boolean validEmail = false;

        if (emailValidation.validateEmail(supplierAddRequest.getSupplierEmail())) {
            validEmail = true;
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: enter a valid email"));
        }

        if (supplierRepository.existsBySupplierCode(supplierAddRequest.getSupplierCode())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        SupplierDetails supplierDetails = new SupplierDetails(supplierAddRequest.getSupplierCode(),
                supplierAddRequest.getSupplierName(), supplierAddRequest.getSupplierAddress(),
                supplierAddRequest.getSupplierEmail(), supplierAddRequest.getSupplierContact(), "Active");

        if(validEmail){
            supplierRepository.save(supplierDetails);
        }
        return ResponseEntity.ok(new MessageResponse("Supplier registered successfully!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editSupplier(@RequestBody SupplierEditRequest supplierEditRequest,@PathVariable("id") long id){

        String nameNew = supplierEditRequest.getName();
        String addressNew = supplierEditRequest.getAddress();
        String emailNew = supplierEditRequest.getEmail();
        String contactNew = supplierEditRequest.getContact();

        SupplierDetails supplierDetails = supplierRepository.findSupplierDetailsById(id).orElse(null);
        supplierDetails.setSupplier_name(nameNew);
        supplierDetails.setSupplier_address(addressNew);
        supplierDetails.setSupplier_email(emailNew);
        supplierDetails.setSupplier_contact_number(contactNew);
        supplierRepository.save(supplierDetails);

        return ResponseEntity.ok(new MessageResponse("Supplier has been edited successfully!"));
    }

    @PutMapping("/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deactivateSupplier(@RequestParam long id) {
        SupplierDetails supplierDetails = supplierRepository.findSupplierDetailsById(id).orElse(null);
        supplierDetails.setSupplierStatus("Inactive");
        supplierRepository.save(supplierDetails);

        return ResponseEntity.ok(new MessageResponse("Supplier has been deactivated successfully!"));
    }

    @PutMapping("/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> activateSupplier(@RequestParam long id) {
        SupplierDetails supplierDetails = supplierRepository.findSupplierDetailsById(id).orElse(null);
        supplierDetails.setSupplierStatus("Active");
        supplierRepository.save(supplierDetails);

        return ResponseEntity.ok(new MessageResponse("Supplier has been activated successfully!"));
    }
}
