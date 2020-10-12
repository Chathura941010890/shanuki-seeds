package com.openuniversity.springjwt.payload.request;

import com.openuniversity.springjwt.models.*;

import java.util.List;

public class BasicInvoiceDetailsRequest {
    private BasicOrderDetails basicOrderDetails;
    private InvoiceTypes invoiceTypes;
    private PaymentMethods paymentMethods;
    private EmployeeDetails employeeDetails;
//    private long grossTotal;
//    private long discountedTotal;
//    private long finalTotal;
    private List <OrderPackets> orderPackets;
    private List <Double> discount;
//    private List <Long> grossValue;
//    private List <Long> discountedValue;
    CreditLimit creditLimit;
    String chequeNo;


    public BasicOrderDetails getBasicOrderDetails() {
        return basicOrderDetails;
    }

    public void setBasicOrderDetails(BasicOrderDetails basicOrderDetails) {
        this.basicOrderDetails = basicOrderDetails;
    }

    public InvoiceTypes getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(InvoiceTypes invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

//    public long getGrossTotal() {
//        return grossTotal;
//    }
//
//    public void setGrossTotal(long grossTotal) {
//        this.grossTotal = grossTotal;
//    }
//
//    public long getDiscountedTotal() {
//        return discountedTotal;
//    }
//
//    public void setDiscountedTotal(long discountedTotal) {
//        this.discountedTotal = discountedTotal;
//    }
//
//    public long getFinalTotal() {
//        return finalTotal;
//    }
//
//    public void setFinalTotal(long finalTotal) {
//        this.finalTotal = finalTotal;
//    }

    public List<OrderPackets> getOrderPackets() {
        return orderPackets;
    }

    public void setOrderPackets(List<OrderPackets> orderPackets) {
        this.orderPackets = orderPackets;
    }

    public List<Double> getDiscount() {
        return discount;
    }

    public void setDiscount(List<Double> discount) {
        this.discount = discount;
    }

//    public List<Long> getGrossValue() {
//        return grossValue;
//    }
//
//    public void setGrossValue(List<Long> grossValue) {
//        this.grossValue = grossValue;
//    }
//
//    public List<Long> getDiscountedValue() {
//        return discountedValue;
//    }
//
//    public void setDiscountedValue(List<Long> discountedValue) {
//        this.discountedValue = discountedValue;
//    }


    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public CreditLimit getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(CreditLimit creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }
}
