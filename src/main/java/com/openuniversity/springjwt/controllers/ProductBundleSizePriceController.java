package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.*;
import com.openuniversity.springjwt.payload.request.ProductBundleSizesPriceQuantityRequest;
import com.openuniversity.springjwt.payload.response.*;
import com.openuniversity.springjwt.repository.ProductBundleSizePriceQuantityRepository;
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
@RequestMapping("/api/productBundlePrice")
public class ProductBundleSizePriceController {

    @Autowired
    ProductBundleSizePriceQuantityRepository productBundleSizePriceQuantityRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProductPacketPrice (){
        boolean exists = false;
        long count = productBundleSizePriceQuantityRepository.count();
        List<ProductBundleSizesPricesQuantity> list= new ArrayList<ProductBundleSizesPricesQuantity>();
        if (count > 0){
            exists = true;
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Nothing have been registered!"));
        }
        if(exists){

            for(long i = 1; i<= count; i++) {
                list.add(productBundleSizePriceQuantityRepository.findById(i));// = customerRepository.findCustomerDetailsById(i).orElse(null);
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Nothing has been registered!"));
        }

        return new ResponseEntity<List<ProductBundleSizesPricesQuantity>>(list, HttpStatus.OK);
    }

    @GetMapping("/one")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOneProductPacketPrice (@RequestBody ProductBundleSizesPriceQuantityRequest productPacketSizesPriceQuantityRequest){
        boolean exists = false;
        ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity;
        if(productBundleSizePriceQuantityRepository.existsByProductDetailsAndBundle(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getBundle())){
            exists = true;
            productBundleSizesPricesQuantity = productBundleSizePriceQuantityRepository.findByProductDetailsAndBundle(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getBundle());
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product has not been registered!"));
        }
        if(exists) {
            return ResponseEntity.ok(new ProductBundleSizesPriceQuantityResponse(
                    productBundleSizesPricesQuantity.getProductDetails(), productBundleSizesPricesQuantity.getBundle(),productBundleSizesPricesQuantity.getBundlePrice()
            ));
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product has not been registered!"));
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPacketPrice(@RequestBody ProductBundleSizesPricesQuantity productPacketSizesPriceQuantityRequest){

        if(productBundleSizePriceQuantityRepository.existsByProductDetailsAndBundle(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getBundle())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product, bundle & price is already available!"));
        }
        else{
            ProductBundleSizesPricesQuantity productPacketSizesPriceQuantity = new ProductBundleSizesPricesQuantity(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getBundle(),
                    productPacketSizesPriceQuantityRequest.getBundlePrice());
            productBundleSizePriceQuantityRepository.save(productPacketSizesPriceQuantity);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("The product, packet & price have been added!"));
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editPacketPrice(@RequestBody ProductBundleSizesPricesQuantity productPacketSizesPriceQuantityRequest, @PathVariable("id") long id){
        ProductDetails newDetails = productPacketSizesPriceQuantityRequest.getProductDetails();
        Bundle newSizes = productPacketSizesPriceQuantityRequest.getBundle();
        long newPrice = productPacketSizesPriceQuantityRequest.getBundlePrice();

        ProductBundleSizesPricesQuantity productPacketSizesPriceQuantity = productBundleSizePriceQuantityRepository.findById(id);
        productPacketSizesPriceQuantity.setProductDetails(newDetails);
        productPacketSizesPriceQuantity.setBundle(newSizes);
        productPacketSizesPriceQuantity.setBundlePrice(newPrice);
        productBundleSizePriceQuantityRepository.save(productPacketSizesPriceQuantity);
        return ResponseEntity.ok(new MessageResponse("The product has been edited successfully!"));
    }

    @GetMapping("/getByProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getByProduct(@RequestParam String productCode){
        ProductDetails productDetails = productRepository.findByProductCode(productCode);

        if(productBundleSizePriceQuantityRepository.existsByProductDetails(productDetails)){
            List <ProductBundleSizesPricesQuantity> productPacketSizesPriceQuantity = productBundleSizePriceQuantityRepository.findByProductDetails(productDetails);
            List <BundleSizesForAProductResponse> list = new ArrayList<BundleSizesForAProductResponse>();

            for( int i = 0; i< productPacketSizesPriceQuantity.size(); i++){
                ProductBundleSizesPricesQuantity x = productPacketSizesPriceQuantity.get(i);
                Bundle z = x.getBundle();
                BundleSizesForAProductResponse a = new BundleSizesForAProductResponse(z);
                list.add(a);
            }
            return new ResponseEntity<List<BundleSizesForAProductResponse>>(list, HttpStatus.OK);
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("The product, Bundle & price not available!"));
        }
    }
}
