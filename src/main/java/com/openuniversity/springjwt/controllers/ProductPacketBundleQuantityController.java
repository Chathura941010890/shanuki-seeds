package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.*;
import com.openuniversity.springjwt.payload.request.*;
import com.openuniversity.springjwt.payload.response.*;
import com.openuniversity.springjwt.repository.*;
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
@RequestMapping("/api/productQuantity")
public class ProductPacketBundleQuantityController {
    @Autowired
    ProductSupplierQuantityRepository productSupplierQuantityRepository;

    @Autowired
    PSQPacketQuantityRepository psqPacketQuantityRepository;

    @Autowired
    PSQBundleQuantityRepository psqBundleQuantityRepository;

    @Autowired
    ProductPacketSizePriceQuantityRepository productPacketSizePriceQuantityRepository;

    @Autowired
    ProductBundleSizePriceQuantityRepository productBundleSizePriceQuantityRepository;

    @Autowired
    PPPQuantityRepository pppQuantityRepository;

    @Autowired
    PBPQuantityRepository pbpQuantityRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PacketSizeRepository packetSizeRepository;

    @Autowired
    BundleDetailsRepository bundleDetailsRepository;

    //add to Product Supplier Quantity Table
    @PostMapping("/addToPSQ")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addToPSQ(@RequestBody ProductSupplierQuantityRequest productSupplierQuantityRequest){
        ProductSupplierQuantity productSupplierQuantity = new ProductSupplierQuantity(
                productSupplierQuantityRequest.getLotNo(),
                productSupplierQuantityRequest.getProductDetails(),
                productSupplierQuantityRequest.getSupplierDetails(),
                productSupplierQuantityRequest.getQuantity(),
                productSupplierQuantityRequest.getPrice(), false);
        productSupplierQuantityRepository.save(productSupplierQuantity);

        return ResponseEntity.ok(new MessageResponse("The product and quantity have been added successfully!"));
    }


    //add to PSQPacketQuantity & PSQBundle Quantity Tables. It automatically Updates PPPQuantity & PBPQuantity tables
    @PostMapping("/addToPSQPacketQuantity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addToPSQPacketQuantity(@RequestBody PSQPacketQuantityRequest packetQuantityRequest){
        boolean added = false;

        if(packetQuantityRequest.getProductSupplierQuantity().getAdded()){
            added = true;
        }
        if(!added) {
            PSQPacketQuantity psqPacketQuantity = new PSQPacketQuantity(packetQuantityRequest.getProductSupplierQuantity(),
                    packetQuantityRequest.getPacketSizes(),
                    packetQuantityRequest.getQuantity());

            long oldPacketQuantity= 0L;
            long newPacketQuantity= 0L;
            if(productPacketSizePriceQuantityRepository.existsByProductDetailsAndPacketSizes(packetQuantityRequest.getProductSupplierQuantity().getProductDetails(), packetQuantityRequest.getPacketSizes())) {
                ProductPacketSizesPriceQuantity x = productPacketSizePriceQuantityRepository.findByProductDetailsAndPacketSizes(packetQuantityRequest.getProductSupplierQuantity().getProductDetails(), packetQuantityRequest.getPacketSizes());
                PPPQuantity pppQuantity = pppQuantityRepository.findByProductPacketSizesPriceQuantity(x);
                oldPacketQuantity = pppQuantityRepository.findByProductPacketSizesPriceQuantity(x).getPacketQuantity();
                newPacketQuantity = oldPacketQuantity + packetQuantityRequest.getQuantity();
                //ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity = productPacketSizePriceQuantityRepository.findByProductDetailsAndPacketSizes(packetQuantityRequest.getProductSupplierQuantity().getProductDetails(), packetQuantityRequest.getPacketSizes());
                pppQuantity.setPacketQuantity(newPacketQuantity);
                //productPacketSizePriceQuantityRepository.save(productPacketSizesPriceQuantity);
                pppQuantityRepository.save(pppQuantity);
            }
            else{
                ProductPacketSizesPriceQuantity packetSizesPriceQuantity = new ProductPacketSizesPriceQuantity(packetQuantityRequest.getProductSupplierQuantity().getProductDetails(),
                        packetQuantityRequest.getPacketSizes(), 0L);
                productPacketSizePriceQuantityRepository.save(packetSizesPriceQuantity);
                PPPQuantity pppQuantity = new PPPQuantity(packetSizesPriceQuantity, packetQuantityRequest.getQuantity());
                pppQuantityRepository.save(pppQuantity);
            }
            psqPacketQuantityRepository.save(psqPacketQuantity);
            packetQuantityRequest.getProductSupplierQuantity().setAdded(true);
            productSupplierQuantityRepository.save(packetQuantityRequest.getProductSupplierQuantity());
            return ResponseEntity.ok(new MessageResponse("The packet quantities have been added successfully!"));
        }else {
            return ResponseEntity.ok(new MessageResponse("The packet & bundle quantity have already been added!"));
        }
    }

    @PostMapping("/addToPSQBundleQuantity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addToPSQBundleQuantity(@RequestBody PSQBundleQuantityRequest psqBundleQuantityRequest){
        PSQBundleQuantity psqBundleQuantity = new PSQBundleQuantity(psqBundleQuantityRequest.getProductSupplierQuantity(),
                psqBundleQuantityRequest.getBundle(), psqBundleQuantityRequest.getQuantity());

        long oldBundleQuantity= 0L;
        long newBundleQuantity= 0L;
        if(productBundleSizePriceQuantityRepository.existsByProductDetailsAndBundle(psqBundleQuantityRequest.getProductSupplierQuantity().getProductDetails(), psqBundleQuantityRequest.getBundle())) {
            ProductBundleSizesPricesQuantity productBundleSizePriceQuantity = productBundleSizePriceQuantityRepository.findByProductDetailsAndBundle(psqBundleQuantityRequest.getProductSupplierQuantity().getProductDetails(), psqBundleQuantityRequest.getBundle());
            PBPQuantity pbpQuantity = pbpQuantityRepository.findByProductBundleSizesPricesQuantity(productBundleSizePriceQuantity);
            oldBundleQuantity = pbpQuantityRepository.findByProductBundleSizesPricesQuantity(productBundleSizePriceQuantity).getBundleQuantity();
            newBundleQuantity = oldBundleQuantity + psqBundleQuantityRequest.getQuantity();
            pbpQuantity.setBundleQuantity(newBundleQuantity);
            pbpQuantityRepository.save(pbpQuantity);
        }
        else{
            ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity = new ProductBundleSizesPricesQuantity(psqBundleQuantityRequest.getProductSupplierQuantity().getProductDetails(),
                    psqBundleQuantityRequest.getBundle(), 0L);
            productBundleSizePriceQuantityRepository.save(productBundleSizesPricesQuantity);
            PBPQuantity pbpQuantity = new PBPQuantity(productBundleSizesPricesQuantity, psqBundleQuantityRequest.getQuantity());
            pbpQuantityRepository.save(pbpQuantity);
        }
        psqBundleQuantityRepository.save(psqBundleQuantity);
        return ResponseEntity.ok(new MessageResponse("The packet quantities have been added successfully!"));
    }

    //get the details in the store
    @GetMapping("/getByProductAndPacketSize/{productCode}/{packetName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getByProductAndPacketSize(@PathVariable("productCode") String productCode, @PathVariable("packetName") String packetName){
        boolean exists = false;
        ProductDetails productDetails = productRepository.findByProductCode(productCode);
        PacketSizes packetSizes = packetSizeRepository.findBySizeName(packetName);

        if(productPacketSizePriceQuantityRepository.existsByProductDetailsAndPacketSizes(productDetails,packetSizes)){
            exists = true;
        }
        else{
            return ResponseEntity.ok(new MessageResponse("The product is not found!"));
        }

        if (exists){
            ProductPacketSizesPriceQuantity ppspq = productPacketSizePriceQuantityRepository.findByProductDetailsAndPacketSizes(productDetails,packetSizes);
            PPPQuantity pppq = pppQuantityRepository.findByProductPacketSizesPriceQuantity(ppspq);
            return ResponseEntity.ok(new PPPQuantityResponse(ppspq, pppq.getPacketQuantity()));
        }
        else{
            return ResponseEntity.ok(new MessageResponse("The product is not found!"));
        }
    }

    @GetMapping("/getByProductAndBundleSize/{productCode}/{bundleName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getByProductAndBundleSize(@PathVariable("productCode") String productCode, @PathVariable("bundleName") String bundleName){
        boolean exists = false;

        ProductDetails productDetails = productRepository.findByProductCode(productCode);
        Bundle bundle = bundleDetailsRepository.findByBundleName(bundleName);

        if(productBundleSizePriceQuantityRepository.existsByProductDetailsAndBundle(productDetails,bundle)){
            exists = true;
        }
        else{
            return ResponseEntity.ok(new MessageResponse("The product is not found!"));
        }

        if (exists){
            ProductBundleSizesPricesQuantity ppspq = productBundleSizePriceQuantityRepository.findByProductDetailsAndBundle(productDetails,bundle);
            PBPQuantity pppq = pbpQuantityRepository.findByProductBundleSizesPricesQuantity(ppspq);
            return ResponseEntity.ok(new PBPQuantityResponse(ppspq, pppq.getBundleQuantity()));
        }
        else{
            return ResponseEntity.ok(new MessageResponse("The product is not found!"));
        }
    }

    @GetMapping("/getByProduct/{productCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getByProduct(@PathVariable("productCode") String productCode){
        boolean exists = false;
        long packetCount = productPacketSizePriceQuantityRepository.count();
        long bundleCount = productBundleSizePriceQuantityRepository.count();
        ProductDetails productDetails = null;

        if(productRepository.existsByProductCode(productCode)){
            exists = true;
            productDetails = productRepository.findByProductCode(productCode);
        }

        if(exists){
            List <ProductPacketSizesPriceQuantity> productPacketSizesPriceQuantity = productPacketSizePriceQuantityRepository.findByProductDetails(productDetails);
            List <ProductBundleSizesPricesQuantity> productBundleSizesPricesQuantities = productBundleSizePriceQuantityRepository.findByProductDetails(productDetails);
            List <QuantityByProductResponse> quantityByProductResponses = new ArrayList<QuantityByProductResponse>();
            for( int i = 0; i< productPacketSizesPriceQuantity.size(); i++){
                ProductPacketSizesPriceQuantity x = productPacketSizesPriceQuantity.get(i);
                ProductDetails y = x.getProductId();
                PacketSizes z = x.getPacketSizes();
                long quantity = pppQuantityRepository.findByProductPacketSizesPriceQuantity(x).getPacketQuantity();
                QuantityByProductResponse a = new QuantityByProductResponse(y,z,quantity);
                quantityByProductResponses.add(a);
            }
            return new ResponseEntity<List<QuantityByProductResponse>>(quantityByProductResponses, HttpStatus.OK);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("The product is not found!"));
        }
    }

    @GetMapping("/getAllPacketQuantity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPacketQuantity(){
        long count = pppQuantityRepository.count();
        List <PPPQuantity> list = new ArrayList<PPPQuantity>();
        boolean exists = false;

        if(count > 0){
            exists = true;
        }

        if(exists){
            for(long i = 1; i<= count; i++){
                list.add(pppQuantityRepository.findById(i));
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No Packets available!"));
        }
        return new ResponseEntity<List<PPPQuantity>>(list, HttpStatus.OK);
    }

    @GetMapping("/getAllBundleQuantity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBundleQuantity(){
        long count = pbpQuantityRepository.count();
        List <PBPQuantity> list = new ArrayList<PBPQuantity>();
        boolean exists = false;

        if(count > 0){
            exists = true;
        }

        if(exists){
            for(long i = 1; i<= count; i++){
                list.add(pbpQuantityRepository.findById(i));
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No Packets available!"));
        }
        return new ResponseEntity<List<PBPQuantity>>(list, HttpStatus.OK);
    }


    //see what happened to the brought bulk
    @GetMapping("/bulkToPackets")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> bulkToPackets(@RequestParam long id){
        ProductSupplierQuantity productSupplierQuantity = productSupplierQuantityRepository.findById(id);
        List <PSQPacketQuantity> psqPacketQuantity = psqPacketQuantityRepository.findByProductSupplierQuantity(productSupplierQuantity);

        return new ResponseEntity<List<PSQPacketQuantity>>(psqPacketQuantity, HttpStatus.OK);

    }

    @GetMapping("/bulkToBundle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> bulkToBundle(@RequestParam long id){
        ProductSupplierQuantity productSupplierQuantity = productSupplierQuantityRepository.findById(id);
        List <PSQBundleQuantity> psqBundleQuantity = psqBundleQuantityRepository.findByProductSupplierQuantity(productSupplierQuantity);

        return new ResponseEntity<List<PSQBundleQuantity>>(psqBundleQuantity, HttpStatus.OK);

    }

    //delete from Product Supplier Quantity
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePSQ(@PathVariable("id") long id){
        ProductSupplierQuantity productSupplierQuantity = productSupplierQuantityRepository.findById(id);
        boolean added = productSupplierQuantityRepository.findById(id).getAdded();

        if(added){
            return ResponseEntity.ok(new MessageResponse("You cannot delete this!"));
        }
        else{
            productSupplierQuantityRepository.delete(productSupplierQuantity);
            return ResponseEntity.ok(new MessageResponse("Successfully deleted!"));
        }
    }

    @GetMapping("/getAllPSQ")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPSQ(){
        long count = productSupplierQuantityRepository.count();

        List<ProductSupplierQuantity> productSupplierQuantities = new ArrayList<ProductSupplierQuantity>();

        for(long i = 1; i <= count; i++){
            if(productSupplierQuantityRepository.findById(i) != null) {
                productSupplierQuantities.add(productSupplierQuantityRepository.findById(i));
            }
        }
        return new ResponseEntity<List<ProductSupplierQuantity>>(productSupplierQuantities, HttpStatus.OK);
    }
}
