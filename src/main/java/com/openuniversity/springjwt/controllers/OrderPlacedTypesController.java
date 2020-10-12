package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.OrderPlacedTypes;
import com.openuniversity.springjwt.payload.request.OrderPlacedTypesRequest;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.OrderTypeRepository;
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
@RequestMapping("/api/orderPlacedTypes")
public class OrderPlacedTypesController {
    @Autowired
    OrderTypeRepository orderTypeRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrderPlacedTypes(){
        long count = orderTypeRepository.count();

        List<OrderPlacedTypes> list = new ArrayList<OrderPlacedTypes>();
        if(count>0){
            for(long i=0; i<=count; i++) {
                list.add(orderTypeRepository.findById(i));
            }
            return new ResponseEntity<List<OrderPlacedTypes>>(list, HttpStatus.OK);
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No types available!"));
        }
    }

    @PostMapping("add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody OrderPlacedTypesRequest orderPlacedTypesRequest){
        if(orderTypeRepository.existsByType(orderPlacedTypesRequest.getType())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Type is already available!"));
        }
        else{
            OrderPlacedTypes orderPlacedTypes = new OrderPlacedTypes(orderPlacedTypesRequest.getType());
            orderTypeRepository.save(orderPlacedTypes);
            return ResponseEntity.ok(new MessageResponse("Order-Placed-Type added successfully!"));
        }
    }
}
