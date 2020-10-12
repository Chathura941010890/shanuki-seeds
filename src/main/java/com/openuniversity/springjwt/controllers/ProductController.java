package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.ProductDetails;
import com.openuniversity.springjwt.payload.request.ProductDetailsRequest;;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.payload.response.ProductDetailsResponse;
import com.openuniversity.springjwt.repository.ProductRepository;
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
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/one/{productCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProductDetails(@PathVariable("productCode") String productCode) {
            boolean productExists = false;
            ProductDetails productDetails;
            if(productRepository.existsByProductCode(productCode)){
                productExists = true;
                productDetails = productRepository.findByProductCode(productCode);
            }
            else {
                if(productRepository.existsByProductName(productCode)){
                    productDetails = productRepository.findByProductName(productCode);
                }
                else{
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: The product has not been registered!"));

                }
            }
            if(productExists) {
                return ResponseEntity.ok(new ProductDetailsResponse(
                        productDetails.getProductCode(), productDetails.getProductName()
                ));
            }
            else{
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: The product has not been registered!"));
            }
        }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProductDetails() {
        boolean productExists = false;
        long count = productRepository.count();
        List<ProductDetails> list= new ArrayList<ProductDetails>();
        if (count > 0){
            productExists = true;
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No products have been registered!"));
        }
        if(productExists){

            for(long i = 1; i<= count; i++) {
                list.add(productRepository.findById(i));// = customerRepository.findCustomerDetailsById(i).orElse(null);
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product has not been registered!"));
        }

        return new ResponseEntity<List<ProductDetails>>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody ProductDetailsRequest productDetailsRequest){

        if (productRepository.existsByProductCode(productDetailsRequest.getCode())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product is already available!"));
        }
        else if (productRepository.existsByProductName(productDetailsRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product is already available!"));
        }
        else {
            ProductDetails productDetails = new ProductDetails(productDetailsRequest.getCode(),
                    productDetailsRequest.getName());
            productRepository.save(productDetails);
        }

        return ResponseEntity.ok(new MessageResponse("The product has been added successfully!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@RequestBody ProductDetailsRequest productDetailsRequest, @PathVariable("id") long id){

        String newCode = productDetailsRequest.getCode();
        String newName = productDetailsRequest.getName();


            ProductDetails productDetails = productRepository.findById(id);
            productDetails.setProductName(newName);
            productDetails.setProductCode(newCode);
            productRepository.save(productDetails);
        return ResponseEntity.ok(new MessageResponse("The product has been edited successfully!"));
    }

}
