package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductDetails;
import com.openuniversity.springjwt.models.ProductPacketSizesPriceQuantity;
import com.openuniversity.springjwt.payload.request.ProductPacketSizesPriceQuantityRequest;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.payload.response.PacketSizesForAProductResponse;
import com.openuniversity.springjwt.payload.response.ProductPacketSizesPriceQuantityResponse;
import com.openuniversity.springjwt.payload.response.QuantityByProductResponse;
import com.openuniversity.springjwt.repository.ProductBundleSizePriceQuantityRepository;
import com.openuniversity.springjwt.repository.ProductPacketSizePriceQuantityRepository;
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
@RequestMapping("/api/productPacketPrice")
public class ProductPacketSizePriceController {
    @Autowired
    ProductPacketSizePriceQuantityRepository productPacketSizePriceQuantityRepository;

    @Autowired
    ProductBundleSizePriceQuantityRepository productBundleSizePriceQuantityRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProductPacketPrice (){
        boolean exists = false;
        long count = productPacketSizePriceQuantityRepository.count();
        List<ProductPacketSizesPriceQuantity> list= new ArrayList<ProductPacketSizesPriceQuantity>();
        if (count > 0){
            exists = true;
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Nothing have been registered!"));
        }
        if(exists){

            for(long i = 1; i<= count; i++) {
                list.add(productPacketSizePriceQuantityRepository.findById(i));// = customerRepository.findCustomerDetailsById(i).orElse(null);
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Nothing has been registered!"));
        }

        return new ResponseEntity<List<ProductPacketSizesPriceQuantity>>(list, HttpStatus.OK);
    }

    @GetMapping("/one")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOneProductPacketPrice (@RequestBody ProductPacketSizesPriceQuantityRequest productPacketSizesPriceQuantityRequest){
        boolean exists = false;
        ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity;
        if(productPacketSizePriceQuantityRepository.existsByProductDetailsAndPacketSizes(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getPacketSizes())){
            exists = true;
            productPacketSizesPriceQuantity = productPacketSizePriceQuantityRepository.findByProductDetailsAndPacketSizes(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getPacketSizes());
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product has not been registered!"));
        }
        if(exists) {
            return ResponseEntity.ok(new ProductPacketSizesPriceQuantityResponse(
                    productPacketSizesPriceQuantity.getProductId(), productPacketSizesPriceQuantity.getPacketSizes(),productPacketSizesPriceQuantity.getPacketPrice()
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
    public ResponseEntity<?> addPacketPrice(@RequestBody ProductPacketSizesPriceQuantityRequest productPacketSizesPriceQuantityRequest){

        if(productPacketSizePriceQuantityRepository.existsByProductDetailsAndPacketSizes(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getPacketSizes())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The product, packet & price is already available!"));
        }
        else{
            ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity = new ProductPacketSizesPriceQuantity(productPacketSizesPriceQuantityRequest.getProductDetails(), productPacketSizesPriceQuantityRequest.getPacketSizes(),
                    productPacketSizesPriceQuantityRequest.getPrice());
            productPacketSizePriceQuantityRepository.save(productPacketSizesPriceQuantity);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("The product, packet & price have been added!"));
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editPacketPrice(@RequestBody ProductPacketSizesPriceQuantityRequest productPacketSizesPriceQuantityRequest, @PathVariable("id") long id){
        ProductDetails newDetails = productPacketSizesPriceQuantityRequest.getProductDetails();
        PacketSizes newSizes = productPacketSizesPriceQuantityRequest.getPacketSizes();
        double newPrice = productPacketSizesPriceQuantityRequest.getPrice();

        ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity = productPacketSizePriceQuantityRepository.findById(id);
        productPacketSizesPriceQuantity.setProductId(newDetails);
        productPacketSizesPriceQuantity.setPacketSizes(newSizes);
        productPacketSizesPriceQuantity.setPacketPrice(newPrice);
        productPacketSizePriceQuantityRepository.save(productPacketSizesPriceQuantity);
        return ResponseEntity.ok(new MessageResponse("The product has been edited successfully!"));
    }

    @GetMapping("/getByProduct/{productCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getByProduct(@PathVariable("productCode") String productCode){
        ProductDetails productDetails = productRepository.findByProductCode(productCode);

        if(productPacketSizePriceQuantityRepository.existsByProductDetails(productDetails)){
            List <ProductPacketSizesPriceQuantity> productPacketSizesPriceQuantity = productPacketSizePriceQuantityRepository.findByProductDetails(productDetails);
            List <PacketSizesForAProductResponse> list = new ArrayList<PacketSizesForAProductResponse>();

            for( int i = 0; i< productPacketSizesPriceQuantity.size(); i++){
                ProductPacketSizesPriceQuantity x = productPacketSizesPriceQuantity.get(i);
                PacketSizes z = x.getPacketSizes();
                PacketSizesForAProductResponse a = new PacketSizesForAProductResponse(z);
                list.add(a);
            }
            return new ResponseEntity<List<PacketSizesForAProductResponse>>(list, HttpStatus.OK);
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("The product, packet & price not available!"));
        }
    }
 }
