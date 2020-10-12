package com.openuniversity.springjwt.controllers;


import com.openuniversity.springjwt.models.*;
import com.openuniversity.springjwt.payload.request.BasicOrderDetailsRequest;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.payload.response.OrderPacketResponse;
import com.openuniversity.springjwt.repository.*;
import com.openuniversity.springjwt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    BasicOrder_PacketsRepository basicOrder_packetsRepository;

    @Autowired
    BundleDetailsRepository bundleDetailsRepository;

    @Autowired
    PacketSizeRepository packetSizeRepository;

    @Autowired
    OrderTypeRepository orderTypeRepository;

    @Autowired
    ProductPacketSizePriceQuantityRepository productPacketSizePriceQuantityRepository;

    @Autowired
    ProductBundleSizePriceQuantityRepository productBundleSizePriceQuantityRepository;

    @Autowired
    PPPQuantityRepository pppQuantityRepository;

    @Autowired
    PBPQuantityRepository pbpQuantityRepository;

    @Autowired
    OrderPacketsRepository orderPacketsRepository;

    @Autowired
    OrderBundleRepository orderBundleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addOrder(@RequestBody BasicOrderDetailsRequest basicOrderDetailsRequest){

        if(basicOrderDetailsRequest.getCustomerDetails() != null && basicOrderDetailsRequest.getDeadline() != null && basicOrderDetailsRequest.getProductPacketSizesPriceQuantity() != null && basicOrderDetailsRequest.getPacketQuantity() != null){
            CustomerDetails customerDetails = basicOrderDetailsRequest.getCustomerDetails();
            Date deadline = basicOrderDetailsRequest.getDeadline();
            List<ProductPacketSizesPriceQuantity> productPacketSizesPriceQuantities = basicOrderDetailsRequest.getProductPacketSizesPriceQuantity();
            List <Long> packetQuantity = basicOrderDetailsRequest.getPacketQuantity();
            List <ProductBundleSizesPricesQuantity> productBundleSizesPricesQuantities = basicOrderDetailsRequest.getProductBundleSizesPricesQuantity();
            List <Long> bundleQuantity = basicOrderDetailsRequest.getBundleQuantity();

            BasicOrderDetails basicOrderDetails = new BasicOrderDetails(customerDetails, deadline, false, false, null, null, null, null, false);

            if(productPacketSizesPriceQuantities.size() == packetQuantity.size()){
                basicOrder_packetsRepository.save(basicOrderDetails);
                for(int i=1; i<packetQuantity.size(); i++){
                    OrderPackets orderPackets = new OrderPackets(basicOrderDetails, productPacketSizesPriceQuantities.get(i),  packetQuantity.get(i));
                    orderPacketsRepository.save(orderPackets);
                }
            }

            if(productBundleSizesPricesQuantities.size() == bundleQuantity.size()){
                for(int i=1; i<bundleQuantity.size(); i++){
                  OrderBundle orderBundle = new OrderBundle(basicOrderDetails, productBundleSizesPricesQuantities.get(i),  bundleQuantity.get(i));
                  orderBundleRepository.save(orderBundle);
                }
            }
            return ResponseEntity.ok(new MessageResponse("Order added successfully!"));
        }
        else {
            return ResponseEntity.ok(new MessageResponse("Order adding unsuccessful!"));
        }
    }


    @PostMapping("/confirm")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> confirmOrder(@RequestParam long id){
        BasicOrderDetails basicOrderDetails = basicOrder_packetsRepository.findById(id);

        List<OrderPackets> orderPackets = orderPacketsRepository.findByBasicOrderDetails(basicOrderDetails);
        List<OrderBundle> orderBundles = orderBundleRepository.findByBasicOrderDetails(basicOrderDetails);

        for(int i =1; i<=orderPackets.size(); i++){
            ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity = orderPackets.get(i).getProductPacketSizesPriceQuantities();
            long quantity = orderPackets.get(i).getQuantity();

            PPPQuantity pppQuantity= pppQuantityRepository.findByProductPacketSizesPriceQuantity(productPacketSizesPriceQuantity);
            long existingQuantity = pppQuantity.getPacketQuantity();

            pppQuantity.setPacketQuantity(existingQuantity - quantity);
            pppQuantityRepository.save(pppQuantity);
        }

        for(int i=1; i<orderBundles.size(); i++){
            ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity = orderBundles.get(i).getProductBundleSizesPricesQuantities();
            long quantity = orderBundles.get(i).getQuantity();

            PBPQuantity pbpQuantity = pbpQuantityRepository.findByProductBundleSizesPricesQuantity(productBundleSizesPricesQuantity);
            long existingQuantity = pbpQuantity.getBundleQuantity();

            pbpQuantity.setBundleQuantity(existingQuantity - quantity);
            pbpQuantityRepository.save(pbpQuantity);
        }

        basicOrderDetails.setConfirmedStatus(true);
        basicOrder_packetsRepository.save(basicOrderDetails);

        return ResponseEntity.ok(new MessageResponse("Order confirming is successful!"));
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editOrder(@RequestBody BasicOrderDetailsRequest basicOrderDetailsRequest,@PathVariable("id") long id){
        BasicOrderDetails basicOrderDetails = basicOrder_packetsRepository.findById(id);

        if(basicOrderDetails.isConfirmedStatus()){
            return ResponseEntity.ok(new MessageResponse("Order cannot be edited "));
        }
        else{
            List<ProductPacketSizesPriceQuantity> productPacketSizesPriceQuantities = basicOrderDetailsRequest.getProductPacketSizesPriceQuantity();
            List <Long> packetQuantity = basicOrderDetailsRequest.getPacketQuantity();
            List <ProductBundleSizesPricesQuantity> productBundleSizesPricesQuantities = basicOrderDetailsRequest.getProductBundleSizesPricesQuantity();
            List <Long> bundleQuantity = basicOrderDetailsRequest.getBundleQuantity();

            if(productPacketSizesPriceQuantities.size() == packetQuantity.size()){
                basicOrder_packetsRepository.save(basicOrderDetails);
                for(int i=1; i<packetQuantity.size(); i++){
                    OrderPackets orderPackets = new OrderPackets(basicOrderDetails, productPacketSizesPriceQuantities.get(i),  packetQuantity.get(i));
                    orderPacketsRepository.save(orderPackets);
                }
            }

            if(productBundleSizesPricesQuantities.size() == bundleQuantity.size()){
                for(int i=1; i<bundleQuantity.size(); i++){
                    OrderBundle orderBundle = new OrderBundle(basicOrderDetails, productBundleSizesPricesQuantities.get(i),  bundleQuantity.get(i));
                    orderBundleRepository.save(orderBundle);
                }
            }
            return ResponseEntity.ok(new MessageResponse("Order edited successfully!"));
        }
    }

    @GetMapping("/allBasicOrderDetails")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> allBasicOrderDetails(){
        long count = basicOrder_packetsRepository.count();

        List<BasicOrderDetails> basicOrderDetails = new ArrayList<BasicOrderDetails>();

        if(count > 0){
            for(long i=1; i<=count; i++){
                basicOrderDetails.add(basicOrder_packetsRepository.findById(i));
            }

            return new ResponseEntity<List<BasicOrderDetails>>(basicOrderDetails, HttpStatus.OK);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("No orders available!"));
        }
    }

    @GetMapping("/getOrderPacketsByBOD/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrderPacketsByBOD(@PathVariable("id") long id){
        BasicOrderDetails basicOrderDetails = basicOrder_packetsRepository.findById(id);
        List <OrderPackets> orderPackets = orderPacketsRepository.findByBasicOrderDetails(basicOrderDetails);
        List<OrderPacketResponse> orderPacketResponse = new ArrayList<OrderPacketResponse>();
        for(int i=1; i<=orderPackets.size(); i++){
            ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity = orderPackets.get(i).getProductPacketSizesPriceQuantities();
            long quantity = orderPackets.get(i).getQuantity();

            orderPacketResponse.add(new OrderPacketResponse(productPacketSizesPriceQuantity, quantity));
        }
        return new ResponseEntity<List<OrderPacketResponse>>(orderPacketResponse, HttpStatus.OK);
    }

    @GetMapping("/getBODByCustomerDetails/{customerCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getBODByCustomerDetails(@PathVariable("customerCode") String customerCode){
        CustomerDetails customerDetails = customerRepository.findCustomerDetailsByCustomerCode(customerCode);

        if(basicOrder_packetsRepository.existsByCustomerDetails(customerDetails)){
            List<BasicOrderDetails> basicOrderDetails = basicOrder_packetsRepository.findByCustomerDetails(customerDetails);

            return new ResponseEntity<List<BasicOrderDetails>>(basicOrderDetails, HttpStatus.OK);
        }
        else {
            return ResponseEntity.ok(new MessageResponse("No orders available for this customer!"));
        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id){
        BasicOrderDetails basicOrderDetails = basicOrder_packetsRepository.findById(id);
        boolean confirmed = basicOrderDetails.isConfirmedStatus();

        if(confirmed){
            List<OrderPackets> orderPackets = orderPacketsRepository.findByBasicOrderDetails(basicOrderDetails);
            List<OrderBundle> orderBundles = orderBundleRepository.findByBasicOrderDetails(basicOrderDetails);

            for(int i =1; i<=orderPackets.size(); i++){
                ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity = orderPackets.get(i).getProductPacketSizesPriceQuantities();
                long quantity = orderPackets.get(i).getQuantity();

                PPPQuantity pppQuantity= pppQuantityRepository.findByProductPacketSizesPriceQuantity(productPacketSizesPriceQuantity);
                long existingQuantity = pppQuantity.getPacketQuantity();

                pppQuantity.setPacketQuantity(existingQuantity + quantity);
                pppQuantityRepository.save(pppQuantity);
                orderPacketsRepository.delete(orderPackets.get(i));
            }

            for(int i=1; i<orderBundles.size(); i++){
                ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity = orderBundles.get(i).getProductBundleSizesPricesQuantities();
                long quantity = orderBundles.get(i).getQuantity();

                PBPQuantity pbpQuantity = pbpQuantityRepository.findByProductBundleSizesPricesQuantity(productBundleSizesPricesQuantity);
                long existingQuantity = pbpQuantity.getBundleQuantity();

                pbpQuantity.setBundleQuantity(existingQuantity + quantity);
                pbpQuantityRepository.save(pbpQuantity);
                orderBundleRepository.delete(orderBundles.get(i));
            }
            basicOrder_packetsRepository.delete(basicOrderDetails);

            return ResponseEntity.ok(new MessageResponse("Order deleted successfully!"));
        }
        else{
            List<OrderPackets> orderPackets = orderPacketsRepository.findByBasicOrderDetails(basicOrderDetails);
            for(int i=1; i<orderPackets.size(); i++){
                orderPacketsRepository.delete(orderPackets.get(i));
            }

            List<OrderBundle> orderBundles = orderBundleRepository.findByBasicOrderDetails(basicOrderDetails);
            for(int i=1; i<orderBundles.size(); i++){
                orderBundleRepository.delete(orderBundles.get(i));
            }
            basicOrder_packetsRepository.delete(basicOrderDetails);
            return ResponseEntity.ok(new MessageResponse("Order deleted!"));
        }
    }

    @PostMapping("/deliveryDetails")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> deliveryDetails(@RequestParam long id, HttpServletRequest request, @RequestBody BasicOrderDetails basicOrderDetails1){
        BasicOrderDetails basicOrderDetails = basicOrder_packetsRepository.findById(id);

        if(basicOrderDetails.isOrderPlacedStatus()){
            return ResponseEntity.ok(new MessageResponse("Order is already delivered!"));
        }
        else{
            JwtUtils jwtUtils = new JwtUtils();
            String username = jwtUtils.getUserNameFromJwtToken(request.getHeader("Authorization"));
            Date date = new Date(System.currentTimeMillis());
            basicOrderDetails.setOrderPlacedTypes(basicOrderDetails1.getOrderPlacedTypes());
            basicOrderDetails.setVehicleNo(basicOrderDetails1.getVehicleNo());
            basicOrderDetails.setEmployeeDetails(employeeRepository.findByUser(userRepository.findByUsername(username).orElse(null)));
            basicOrderDetails.setOrderPlacedStatus(true);
            basicOrderDetails.setOrderPlaced(date);

            basicOrder_packetsRepository.save(basicOrderDetails);

            return ResponseEntity.ok(new MessageResponse("Order delivered successfully!"));
        }
    }

    @GetMapping("/getBODByEmployeeDetails/{employeeCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getBODByEmployeeDetails(@PathVariable("employeeCode") String employeeCode){
        EmployeeDetails employeeDetails = employeeRepository.findEmployeeDetailsByEmployeeCode(employeeCode);

        if(basicOrder_packetsRepository.existsByEmployeeDetails(employeeDetails)){
           List <BasicOrderDetails> basicOrderDetails = basicOrder_packetsRepository.findByEmployeeDetails(employeeDetails);
            return new ResponseEntity<List<BasicOrderDetails>>(basicOrderDetails, HttpStatus.OK);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("This employee has placed no orders!"));
        }
    }

    @GetMapping("/getBODByOrderPlacedStatus/{orderPlacedStatus}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getBODByOrderPlacedStatus(@PathVariable("orderPlacedStatus") boolean orderPlacedStatus){
        if(basicOrder_packetsRepository.existsByOrderPlacedStatus(orderPlacedStatus)){
            List <BasicOrderDetails> basicOrderDetails = basicOrder_packetsRepository.findByOrderPlacedStatus(orderPlacedStatus);
            return new ResponseEntity<List<BasicOrderDetails>>(basicOrderDetails, HttpStatus.OK);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("No orders!"));
        }
    }

    @GetMapping("/getBODByDeadline/{deadline}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getBODByDeadline(@PathVariable("deadline") Date deadline){
        long count = basicOrder_packetsRepository.count();
        List<BasicOrderDetails> basicOrderDetails = new ArrayList<BasicOrderDetails>();
        for(long i = 1; i<=count; i++){
            BasicOrderDetails basicOrderDetails1 = basicOrder_packetsRepository.findById(i);
            if(basicOrderDetails1.getDeadline().before(deadline)){
                basicOrderDetails.add(basicOrderDetails1);
            }
        }

        if(basicOrderDetails.size() > 0){
            return new ResponseEntity<List<BasicOrderDetails>>(basicOrderDetails, HttpStatus.OK);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("No orders before this deadline!"));
        }
    }

    @GetMapping("/getBODByInvoiced/{invoiced}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getBODByInvoiced(@PathVariable("invoiced") boolean invoiced){
        if(basicOrder_packetsRepository.existsByInvoiced(invoiced)){
            List <BasicOrderDetails> basicOrderDetails = basicOrder_packetsRepository.findByInvoiced(invoiced);
            return new ResponseEntity<List<BasicOrderDetails>>(basicOrderDetails, HttpStatus.OK);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("No orders!"));
        }
    }
}