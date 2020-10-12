package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.*;
import com.openuniversity.springjwt.payload.request.BasicInvoiceDetailsRequest;
import com.openuniversity.springjwt.payload.response.InvoiceResponse;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Controller
@RequestMapping("/api/invoice")
public class InvoiceController {
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

    @Autowired
    BasicInvoiceDetailsRepository basicInvoiceDetailsRepository;

    @Autowired
    SecondaryInvoiceDetailsRepository secondaryInvoiceDetailsRepository;


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addInvoice(@RequestParam BasicInvoiceDetailsRequest basicInvoiceDetailsRequest){
        BasicOrderDetails basicOrderDetails = basicInvoiceDetailsRequest.getBasicOrderDetails();
        InvoiceTypes invoiceTypes = basicInvoiceDetailsRequest.getInvoiceTypes();
        PaymentMethods paymentMethods = basicInvoiceDetailsRequest.getPaymentMethods();
        EmployeeDetails employeeDetails = basicOrderDetails.getEmployeeDetails();
        List<OrderPackets> orderPackets = basicInvoiceDetailsRequest.getOrderPackets();
        List <Double> discount = basicInvoiceDetailsRequest.getDiscount();
        List <ProductPacketSizesPriceQuantity> productPacketSizesPriceQuantities = new ArrayList<ProductPacketSizesPriceQuantity>();
        List <Long> packetQuantity = new ArrayList<Long>();
        List <Double> pppDiscount = new ArrayList<Double>();
        List <Double> grossValue = new ArrayList<Double>();
        List <Double> discountedValue = new ArrayList<Double>();
        double grossTotal = 0;
        double discountedTotal = 0;
        double finalTotal = 0;
        String creditLimit = "-";
        String chequeNo = "-";
        if(invoiceTypes.equals("Credit")) {
            creditLimit = basicInvoiceDetailsRequest.getCreditLimit().getLimit();
        }
        if(paymentMethods.equals("Cheque")) {
            chequeNo = basicInvoiceDetailsRequest.getChequeNo();
        }

        BasicInvoiceDetails basicInvoiceDetails = new BasicInvoiceDetails(basicOrderDetails, invoiceTypes, paymentMethods.getMethod(),employeeDetails, grossTotal, discountedTotal, finalTotal, creditLimit, chequeNo);

        if(basicOrderDetails.isOrderPlacedStatus()) {
            for (int i = 1; i <= orderPackets.size(); i++) {
                productPacketSizesPriceQuantities.add(orderPackets.get(i).getProductPacketSizesPriceQuantities());
                packetQuantity.add(orderPackets.get(i).getQuantity());
                pppDiscount.add(discount.get(i));
                grossValue.add(orderPackets.get(i).getProductPacketSizesPriceQuantities().getPacketPrice());

                double x = orderPackets.get(i).getProductPacketSizesPriceQuantities().getPacketPrice();
                double y = (x * (100 - discount.get(i))) / 100;
                double z = (x * discount.get(i))/100;
                discountedValue.add(y);
                grossTotal = grossTotal + x;
                discountedTotal = discountedTotal + z;
                finalTotal = finalTotal + y;

                // SecondaryInvoiceDetails secondaryInvoiceDetails = new SecondaryInvoiceDetails(basicInvoiceDetails, orderPackets.get(i), discount.get(i), orderPackets.get(i).getProductPacketSizesPriceQuantities().getPacketPrice(), );
            }

            basicInvoiceDetailsRepository.save(basicInvoiceDetails);
            for (int i = 1; i <= productPacketSizesPriceQuantities.size(); i++) {
                SecondaryInvoiceDetails secondaryInvoiceDetails = new SecondaryInvoiceDetails(basicInvoiceDetails, orderPackets.get(i), discount.get(i), grossValue.get(i), discountedValue.get(i));
                secondaryInvoiceDetailsRepository.save(secondaryInvoiceDetails);
            }
            return ResponseEntity.ok(new MessageResponse("Invoice added successfully!"));
        }
        else{
            return ResponseEntity.ok(new MessageResponse("Please add delivery details before add the invoice!"));
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllInvoices(){
        String customerCode;
        String customerName;
        String customerAddress;
        long orderId;
        long invoiceId;
        String inVoiceType;
        Date invoiceDate;
        List<Pair> pair = new ArrayList<Pair>();
        String employeeCodeInvoicedBy;
        String employeeNameInvoicedBy;
        String vehicleNo;
        Date deliveryDate;
        double grossTotal;
        double discountedTotal;
        String deliveredBy;
        String creditLimit;
        String paymentMethod;
        String chequeNo;

        long count = basicInvoiceDetailsRepository.count();
        List <InvoiceResponse> invoiceResponses = new ArrayList<InvoiceResponse>();

        for(long i = 1; i <= count; i++){
            BasicInvoiceDetails basicInvoiceDetails = basicInvoiceDetailsRepository.findById(i);
            invoiceId = basicInvoiceDetails.getId();
            inVoiceType = basicInvoiceDetails.getInvoiceTypes().getType();
            invoiceDate = basicInvoiceDetails.getCreatedDate();
            grossTotal = basicInvoiceDetails.getGrossTotal();
            discountedTotal = basicInvoiceDetails.getDiscountedTotal();
            creditLimit = basicInvoiceDetails.getCreditLimit();
            paymentMethod = basicInvoiceDetails.getPaymentMethod();
            chequeNo = basicInvoiceDetails.getChequeNumber();

            BasicOrderDetails basicOrderDetails = basicInvoiceDetails.getBasicOrderDetails();
            orderId = basicOrderDetails.getId();
            vehicleNo = basicOrderDetails.getVehicleNo();
            deliveryDate = basicOrderDetails.getOrderPlaced();
            deliveredBy = basicOrderDetails.getEmployeeDetails().getEmployeeCode();

            EmployeeDetails employeeDetails = basicInvoiceDetails.getEmployeeDetails();
            employeeCodeInvoicedBy = employeeDetails.getEmployeeCode();
            employeeNameInvoicedBy = employeeDetails.getEmployeeName();

            CustomerDetails customerDetails = basicOrderDetails.getCustomerDetails();
            customerCode = customerDetails.getCustomer_code();
            customerName = customerDetails.getCustomer_name();
            customerAddress = customerDetails.getCustomer_address();

            List <SecondaryInvoiceDetails> secondaryInvoiceDetails = secondaryInvoiceDetailsRepository.findByBasicInvoiceDetails(basicInvoiceDetails);
            for(int j =1; j<=secondaryInvoiceDetails.size(); j++){
                String productCode = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductCode();
                String productName = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductName();
                String packetWeight = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketSizes().getPacketWeight();
                long packetQuantity = secondaryInvoiceDetails.get(j).getOrderPackets().getQuantity();
                double unitPrice = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketPrice();
                double eachDiscount = secondaryInvoiceDetails.get(j).getDiscount();
                double eachGrossValue = unitPrice * packetQuantity;
                double eachDiscountedValue = eachGrossValue * (100 - eachDiscount)/100;

                pair.add(new Pair(productCode, productName, packetWeight, packetQuantity, unitPrice, eachDiscount, eachGrossValue, eachDiscountedValue));
            }

           InvoiceResponse x = new InvoiceResponse(customerCode, customerName, customerAddress,
                    orderId, invoiceId, inVoiceType, invoiceDate, pair, employeeCodeInvoicedBy, employeeNameInvoicedBy,
                    vehicleNo, deliveryDate, grossTotal,discountedTotal, deliveredBy, creditLimit, paymentMethod,chequeNo);
            invoiceResponses.add(x);
        }
        return new ResponseEntity<List<InvoiceResponse>>(invoiceResponses, HttpStatus.OK);
    }

    @GetMapping("/getInvoiceByCustomer/{customer}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getInvoiceByCustomer(@PathVariable("customer") String customer){
        String customerCode;
        String customerName;
        String customerAddress;
        long orderId;
        long invoiceId;
        String inVoiceType;
        Date invoiceDate;
        List<Pair> pair = new ArrayList<Pair>();
        String employeeCodeInvoicedBy;
        String employeeNameInvoicedBy;
        String vehicleNo;
        Date deliveryDate;
        double grossTotal;
        double discountedTotal;
        String deliveredBy;
        String creditLimit;
        String paymentMethod;
        String chequeNo;

        CustomerDetails customerDetails1;
        List <BasicOrderDetails> basicOrderDetails1;

        if(customerRepository.existsByCustomerName(customer)){
            customerDetails1 = customerRepository.findCustomerDetailsByCustomerName(customer);
        }else{
            if(customerRepository.existsByCustomerCode(customer)){
                customerDetails1 = customerRepository.findCustomerDetailsByCustomerCode(customer);
            }
            else{
                return ResponseEntity.ok(new MessageResponse("No customers with this name or code!"));
            }
        }

        if(customerDetails1 != null && basicOrder_packetsRepository.existsByCustomerDetails(customerDetails1)){
            basicOrderDetails1 = basicOrder_packetsRepository.findByCustomerDetails(customerDetails1);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("No orders with this customer!"));
        }
        List <InvoiceResponse> invoiceResponses = new ArrayList<InvoiceResponse>();

        for(int i =0; i<basicOrderDetails1.size(); i++) {
            BasicInvoiceDetails basicInvoiceDetails = null;
            if (basicOrderDetails1.get(i).isInvoiced()) {
                basicInvoiceDetails = basicInvoiceDetailsRepository.findByBasicOrderDetails(basicOrderDetails1.get(i));
            }
            if (basicInvoiceDetails != null) {
                invoiceId = basicInvoiceDetails.getId();
                inVoiceType = basicInvoiceDetails.getInvoiceTypes().getType();
                invoiceDate = basicInvoiceDetails.getCreatedDate();
                grossTotal = basicInvoiceDetails.getGrossTotal();
                discountedTotal = basicInvoiceDetails.getDiscountedTotal();
                creditLimit = basicInvoiceDetails.getCreditLimit();
                paymentMethod = basicInvoiceDetails.getPaymentMethod();
                chequeNo = basicInvoiceDetails.getChequeNumber();

                BasicOrderDetails basicOrderDetails = basicInvoiceDetails.getBasicOrderDetails();
                orderId = basicOrderDetails.getId();
                vehicleNo = basicOrderDetails.getVehicleNo();
                deliveryDate = basicOrderDetails.getOrderPlaced();
                deliveredBy = basicOrderDetails.getEmployeeDetails().getEmployeeCode();

                EmployeeDetails employeeDetails = basicInvoiceDetails.getEmployeeDetails();
                employeeCodeInvoicedBy = employeeDetails.getEmployeeCode();
                employeeNameInvoicedBy = employeeDetails.getEmployeeName();

                CustomerDetails customerDetails = basicOrderDetails.getCustomerDetails();
                customerCode = customerDetails.getCustomer_code();
                customerName = customerDetails.getCustomer_name();
                customerAddress = customerDetails.getCustomer_address();

                List<SecondaryInvoiceDetails> secondaryInvoiceDetails = secondaryInvoiceDetailsRepository.findByBasicInvoiceDetails(basicInvoiceDetails);
                for (int j = 1; j <= secondaryInvoiceDetails.size(); j++) {
                    String productCode = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductCode();
                    String productName = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductName();
                    String packetWeight = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketSizes().getPacketWeight();
                    long packetQuantity = secondaryInvoiceDetails.get(j).getOrderPackets().getQuantity();
                    double unitPrice = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketPrice();
                    double eachDiscount = secondaryInvoiceDetails.get(j).getDiscount();
                    double eachGrossValue = unitPrice * packetQuantity;
                    double eachDiscountedValue = eachGrossValue * (100 - eachDiscount) / 100;

                    pair.add(new Pair(productCode, productName, packetWeight, packetQuantity, unitPrice, eachDiscount, eachGrossValue, eachDiscountedValue));
                }

                InvoiceResponse x = new InvoiceResponse(customerCode, customerName, customerAddress,
                        orderId, invoiceId, inVoiceType, invoiceDate, pair, employeeCodeInvoicedBy, employeeNameInvoicedBy,
                        vehicleNo, deliveryDate, grossTotal, discountedTotal, deliveredBy, creditLimit, paymentMethod, chequeNo);
                invoiceResponses.add(x);
            }
        }
        return new ResponseEntity<List<InvoiceResponse>>(invoiceResponses, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        String customerCode;
        String customerName;
        String customerAddress;
        long orderId;
        long invoiceId;
        String inVoiceType;
        Date invoiceDate;
        List<Pair> pair = new ArrayList<Pair>();
        String employeeCodeInvoicedBy;
        String employeeNameInvoicedBy;
        String vehicleNo;
        Date deliveryDate;
        double grossTotal;
        double discountedTotal;
        String deliveredBy;
        String creditLimit;
        String paymentMethod;
        String chequeNo;
        List <InvoiceResponse> invoiceResponses = new ArrayList<InvoiceResponse>();

        BasicInvoiceDetails basicInvoiceDetails = basicInvoiceDetailsRepository.findById(id);
        invoiceId = basicInvoiceDetails.getId();
        inVoiceType = basicInvoiceDetails.getInvoiceTypes().getType();
        invoiceDate = basicInvoiceDetails.getCreatedDate();
        grossTotal = basicInvoiceDetails.getGrossTotal();
        discountedTotal = basicInvoiceDetails.getDiscountedTotal();
        creditLimit = basicInvoiceDetails.getCreditLimit();
        paymentMethod = basicInvoiceDetails.getPaymentMethod();
        chequeNo = basicInvoiceDetails.getChequeNumber();

        BasicOrderDetails basicOrderDetails = basicInvoiceDetails.getBasicOrderDetails();
        orderId = basicOrderDetails.getId();
        vehicleNo = basicOrderDetails.getVehicleNo();
        deliveryDate = basicOrderDetails.getOrderPlaced();
        deliveredBy = basicOrderDetails.getEmployeeDetails().getEmployeeCode();

        EmployeeDetails employeeDetails = basicInvoiceDetails.getEmployeeDetails();
        employeeCodeInvoicedBy = employeeDetails.getEmployeeCode();
        employeeNameInvoicedBy = employeeDetails.getEmployeeName();

        CustomerDetails customerDetails = basicOrderDetails.getCustomerDetails();
        customerCode = customerDetails.getCustomer_code();
        customerName = customerDetails.getCustomer_name();
        customerAddress = customerDetails.getCustomer_address();

        List <SecondaryInvoiceDetails> secondaryInvoiceDetails = secondaryInvoiceDetailsRepository.findByBasicInvoiceDetails(basicInvoiceDetails);
        for(int j =1; j<=secondaryInvoiceDetails.size(); j++){
            String productCode = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductCode();
            String productName = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductName();
            String packetWeight = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketSizes().getPacketWeight();
            long packetQuantity = secondaryInvoiceDetails.get(j).getOrderPackets().getQuantity();
            double unitPrice = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketPrice();
            double eachDiscount = secondaryInvoiceDetails.get(j).getDiscount();
            double eachGrossValue = unitPrice * packetQuantity;
            double eachDiscountedValue = eachGrossValue * (100 - eachDiscount)/100;

            pair.add(new Pair(productCode, productName, packetWeight, packetQuantity, unitPrice, eachDiscount, eachGrossValue, eachDiscountedValue));
        }

        InvoiceResponse x = new InvoiceResponse(customerCode, customerName, customerAddress,
                orderId, invoiceId, inVoiceType, invoiceDate, pair, employeeCodeInvoicedBy, employeeNameInvoicedBy,
                vehicleNo, deliveryDate, grossTotal,discountedTotal, deliveredBy, creditLimit, paymentMethod,chequeNo);
        invoiceResponses.add(x);
        return new ResponseEntity<List<InvoiceResponse>>(invoiceResponses, HttpStatus.OK);
    }

    @GetMapping("/getByEmployee/{employee}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getByEmployee(@PathVariable("employee") String employee){
        String customerCode;
        String customerName;
        String customerAddress;
        long orderId;
        long invoiceId;
        String inVoiceType;
        Date invoiceDate;
        List<Pair> pair = new ArrayList<Pair>();
        String employeeCodeInvoicedBy;
        String employeeNameInvoicedBy;
        String vehicleNo;
        Date deliveryDate;
        double grossTotal;
        double discountedTotal;
        String deliveredBy;
        String creditLimit;
        String paymentMethod;
        String chequeNo;
        List <InvoiceResponse> invoiceResponses = new ArrayList<InvoiceResponse>();

        EmployeeDetails employeeDetails1;
        List <BasicInvoiceDetails> basicInvoiceDetails1;

        if(employeeRepository.existsByEmployeeName(employee)){
            employeeDetails1 = employeeRepository.findEmployeeDetailsByEmployeeName(employee);
        }else{
            if(employeeRepository.existsByEmployeeCode(employee)){
                employeeDetails1 = employeeRepository.findEmployeeDetailsByEmployeeCode(employee);
            }
            else{
                return ResponseEntity.ok(new MessageResponse("No customers with this name or code!"));
            }
        }

        if(basicInvoiceDetailsRepository.existsByEmployeeDetails(employeeDetails1)){
            basicInvoiceDetails1 = basicInvoiceDetailsRepository.findByEmployeeDetails(employeeDetails1);
        }
        else{
            return ResponseEntity.ok(new MessageResponse("No invoices with this name or code!"));
        }

        for(int i =0; i<basicInvoiceDetails1.size(); i++) {
            BasicInvoiceDetails basicInvoiceDetails = null;
            basicInvoiceDetails =basicInvoiceDetails1.get(i);
            if (basicInvoiceDetails != null) {
                invoiceId = basicInvoiceDetails.getId();
                inVoiceType = basicInvoiceDetails.getInvoiceTypes().getType();
                invoiceDate = basicInvoiceDetails.getCreatedDate();
                grossTotal = basicInvoiceDetails.getGrossTotal();
                discountedTotal = basicInvoiceDetails.getDiscountedTotal();
                creditLimit = basicInvoiceDetails.getCreditLimit();
                paymentMethod = basicInvoiceDetails.getPaymentMethod();
                chequeNo = basicInvoiceDetails.getChequeNumber();

                BasicOrderDetails basicOrderDetails = basicInvoiceDetails.getBasicOrderDetails();
                orderId = basicOrderDetails.getId();
                vehicleNo = basicOrderDetails.getVehicleNo();
                deliveryDate = basicOrderDetails.getOrderPlaced();
                deliveredBy = basicOrderDetails.getEmployeeDetails().getEmployeeCode();

                EmployeeDetails employeeDetails = basicInvoiceDetails.getEmployeeDetails();
                employeeCodeInvoicedBy = employeeDetails.getEmployeeCode();
                employeeNameInvoicedBy = employeeDetails.getEmployeeName();

                CustomerDetails customerDetails = basicOrderDetails.getCustomerDetails();
                customerCode = customerDetails.getCustomer_code();
                customerName = customerDetails.getCustomer_name();
                customerAddress = customerDetails.getCustomer_address();

                List<SecondaryInvoiceDetails> secondaryInvoiceDetails = secondaryInvoiceDetailsRepository.findByBasicInvoiceDetails(basicInvoiceDetails);
                for (int j = 1; j <= secondaryInvoiceDetails.size(); j++) {
                    String productCode = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductCode();
                    String productName = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getProductId().getProductName();
                    String packetWeight = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketSizes().getPacketWeight();
                    long packetQuantity = secondaryInvoiceDetails.get(j).getOrderPackets().getQuantity();
                    double unitPrice = secondaryInvoiceDetails.get(j).getOrderPackets().getProductPacketSizesPriceQuantities().getPacketPrice();
                    double eachDiscount = secondaryInvoiceDetails.get(j).getDiscount();
                    double eachGrossValue = unitPrice * packetQuantity;
                    double eachDiscountedValue = eachGrossValue * (100 - eachDiscount) / 100;

                    pair.add(new Pair(productCode, productName, packetWeight, packetQuantity, unitPrice, eachDiscount, eachGrossValue, eachDiscountedValue));
                }

                InvoiceResponse x = new InvoiceResponse(customerCode, customerName, customerAddress,
                        orderId, invoiceId, inVoiceType, invoiceDate, pair, employeeCodeInvoicedBy, employeeNameInvoicedBy,
                        vehicleNo, deliveryDate, grossTotal, discountedTotal, deliveredBy, creditLimit, paymentMethod, chequeNo);
                invoiceResponses.add(x);
            }
        }
        return new ResponseEntity<List<InvoiceResponse>>(invoiceResponses, HttpStatus.OK);
    }

}
