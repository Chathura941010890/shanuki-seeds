package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.CreditLimit;
import com.openuniversity.springjwt.models.InvoiceTypes;
import com.openuniversity.springjwt.models.PaymentMethods;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.CreditLimitRepository;
import com.openuniversity.springjwt.repository.InvoiceTypesRepository;
import com.openuniversity.springjwt.repository.PaymentMethodRepository;
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
@RequestMapping("/api/IPC")
public class InvoicePaymentCreditController {
    @Autowired
    InvoiceTypesRepository invoiceTypesRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    CreditLimitRepository creditLimitRepository;

    @GetMapping("/getAllInvoiceTypes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllInvoiceTypes(){
        long count = invoiceTypesRepository.count();
        List <InvoiceTypes> invoiceTypes = new ArrayList<InvoiceTypes>();

        for(long i =1; i <= count; i++){
            invoiceTypes.add(invoiceTypesRepository.findById(i));
        }

        return new ResponseEntity<List<InvoiceTypes>>(invoiceTypes, HttpStatus.OK);
    }

    @GetMapping("/getAllPaymentTypes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPaymentTypes(){
        long count = paymentMethodRepository.count();
        List <PaymentMethods> paymentMethods = new ArrayList<PaymentMethods>();

        for(long i =1; i <= count; i++){
            paymentMethods.add(paymentMethodRepository.findById(i));
        }

        return new ResponseEntity<List<PaymentMethods>>(paymentMethods, HttpStatus.OK);
    }

    @GetMapping("/getAllCreditLimits")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllCreditLimits(){
        long count = creditLimitRepository.count();
        List <CreditLimit> creditLimits = new ArrayList<CreditLimit>();

        for(long i =1; i <= count; i++){
            creditLimits.add(creditLimitRepository.findById(i));
        }

        return new ResponseEntity<List<CreditLimit>>(creditLimits, HttpStatus.OK);
    }

    @PostMapping("/addInvoiceType")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addInvoiceType(@RequestParam String type){
        InvoiceTypes invoiceTypes = new InvoiceTypes(type);
        invoiceTypesRepository.save(invoiceTypes);
        return ResponseEntity.ok(new MessageResponse("Invoice type added successfully!"));
    }

    @PostMapping("/addPaymentMethod")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPaymentMethod(@RequestParam String type){
        PaymentMethods invoiceTypes = new PaymentMethods(type);
        paymentMethodRepository.save(invoiceTypes);
        return ResponseEntity.ok(new MessageResponse("Payment method added successfully!"));
    }

    @PostMapping("/addCreditLimit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCreditLimit(@RequestParam String type){
        CreditLimit invoiceTypes = new CreditLimit(type);
        creditLimitRepository.save(invoiceTypes);
        return ResponseEntity.ok(new MessageResponse("Credit limit added successfully!"));
    }

}


