package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.Bundle;
import com.openuniversity.springjwt.payload.request.BundleAddRequest;
import com.openuniversity.springjwt.payload.request.BundleEditRequest;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.BundleDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Controller
@RequestMapping("/api/bundle")
public class BundleController {

    @Autowired
    BundleDetailsRepository bundleDetailsRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBundleDetails() {
        boolean bundleExists = false;
        List <Long> id = bundleDetailsRepository.getAllIds();
        long count = Collections.max(id);
        List<Bundle> list= new ArrayList<Bundle>();
        if (count > 0){
            bundleExists = true;
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No packet sizes available!"));
        }
        if(bundleExists){

            for(long i = 1; i<= count; i++) {
                list.add(bundleDetailsRepository.getAllById(i));
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No packet sizes available!"));
        }

        return new ResponseEntity<List<Bundle>>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addBundle(@RequestBody BundleAddRequest bundleAddRequest){

        if (bundleDetailsRepository.existsByPacketQuantity(bundleAddRequest.getPacketQuantity())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Packet size is already taken!"));
        }
        else {
            Bundle bundle = new Bundle(bundleAddRequest.getBundleName(),
                    bundleAddRequest.getPacketQuantity());

            bundleDetailsRepository.save(bundle);
            return ResponseEntity.ok(new MessageResponse("Packet size added successfully!"));
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <?> editBundle(@RequestBody BundleEditRequest bundleEditRequest, @PathVariable("id") long id){
        String newSizeName = bundleEditRequest.getBundleName();
        long newPacketWeight = bundleEditRequest.getPacketQuantity();

        if (bundleDetailsRepository.existsByPacketQuantity(bundleEditRequest.getPacketQuantity())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Packet size is already taken!"));
        }
        else {
            Bundle bundle = bundleDetailsRepository.findById(id);
            bundle.setBundleName(newSizeName);
            bundle.setPacketQuantity(newPacketWeight);

            bundleDetailsRepository.save(bundle);

            return ResponseEntity.ok(new MessageResponse("Packet size edited successfully!"));
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <?> deleteBundle(@PathVariable("id") long id){
        Bundle bundle = bundleDetailsRepository.findById(id);
        bundleDetailsRepository.delete(bundle);
        return ResponseEntity.ok(new MessageResponse("Packet size deleted successfully!"));
    }

}
