package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.CustomerDetails;
import com.openuniversity.springjwt.payload.request.CustomerAddRequest;
import com.openuniversity.springjwt.payload.request.CustomerDetailsRequest;
import com.openuniversity.springjwt.payload.request.CustomerEditRequest;
import com.openuniversity.springjwt.payload.response.CustomerDetailsResponse;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.CustomerRepository;
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
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmailValidation emailValidation;


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllCustomerDetails() {
        boolean customersExists = false;
        long count = customerRepository.count();
       List <CustomerDetails> list= new ArrayList<CustomerDetails>();
        if (count > 0){
            customersExists = true;
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No customers have not been registered!"));
        }
        if(customersExists){

            for(long i = 1; i<= count; i++) {
                list.add(customerRepository.findCustomerDetailsById(i).orElse(null));// = customerRepository.findCustomerDetailsById(i).orElse(null);
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The customer has not been registered!"));
        }

        return new ResponseEntity<List<CustomerDetails>>(list, HttpStatus.OK);
    }

    @GetMapping("/one/{customerCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCustomerDetails(@PathVariable("customerCode") String customerCode) {
        boolean customerExists = false;
        boolean customerActive = false;

        CustomerDetails customerDetails = new CustomerDetails();
        if (customerRepository.existsByCustomerCode(customerCode)){
            customerExists = true;
            customerDetails = customerRepository.findCustomerDetailsByCustomerCode(customerCode);
            //customerDetails = new CustomerDetails(customerCode);
        } else {
            if(customerRepository.existsByCustomerName(customerCode)){
                customerDetails = customerRepository.findCustomerDetailsByCustomerName(customerCode);
            }
            else{
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: The customer has not been registered!"));
            }
        }

        if(customerDetails.getCustomerStatus().equals("Active")){
            customerActive = true;
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The customer is not activated!"));
        }
        if(customerExists){
            return ResponseEntity.ok(new CustomerDetailsResponse(
                    customerDetails.getCustomer_code(),customerDetails.getCustomer_name(),
                    customerDetails.getCustomer_address(), customerDetails.getCustomer_email(),
                    customerDetails.getCustomer_contact_number()
            ));
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The customer has not been registered!"));
        }
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerAddRequest customerAddRequest){
        boolean validEmail = false;

        if (emailValidation.validateEmail(customerAddRequest.getCustomerEmail())) {
            validEmail = true;
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: enter a valid email"));
        }

        if (customerRepository.existsByCustomerCode(customerAddRequest.getCustomerCode())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        CustomerDetails customerDetails = new CustomerDetails(customerAddRequest.getCustomerCode(),
                customerAddRequest.getCustomerName(), customerAddRequest.getCustomerAddress(),
                customerAddRequest.getCustomerEmail(), customerAddRequest.getCustomerContact(), "Active");

        if(validEmail){
            customerRepository.save(customerDetails);
        }
        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editCustomer(@RequestBody CustomerEditRequest customerEditRequest,@PathVariable("id") long id){

        String nameNew = customerEditRequest.getName();
        String addressNew = customerEditRequest.getAddress();
        String emailNew = customerEditRequest.getEmail();
        String contactNew = customerEditRequest.getContact();

        CustomerDetails customerDetails = customerRepository.findCustomerDetailsById(id).orElse(null);
        customerDetails.setCustomer_name(nameNew);
        customerDetails.setCustomer_address(addressNew);
        customerDetails.setCustomer_email(emailNew);
        customerDetails.setCustomer_contact_number(contactNew);
        customerRepository.save(customerDetails);

        return ResponseEntity.ok(new MessageResponse("Customer has been edited successfully!"));
    }

    @PutMapping("/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deactivateCustomer(@RequestParam long id) {
        CustomerDetails customerDetails = customerRepository.findCustomerDetailsById(id).orElse(null);
        customerDetails.setCustomerStatus("Inactive");
        customerRepository.save(customerDetails);

        return ResponseEntity.ok(new MessageResponse("Customer has been deactivated successfully!"));
    }

    @PutMapping("/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> activateCustomer(@RequestParam long id) {
        CustomerDetails customerDetails = customerRepository.findCustomerDetailsById(id).orElse(null);
        customerDetails.setCustomerStatus("Active");
        customerRepository.save(customerDetails);

        return ResponseEntity.ok(new MessageResponse("Customer has been activated successfully!"));
    }
}
