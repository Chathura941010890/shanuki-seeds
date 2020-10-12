package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.Pair;

import java.util.Date;
import java.util.List;

public class InvoiceResponse {
    private String customerCode;

    private String customerName;

    private String customerAddress;

    private long orderId;

    private long invoiceId;

    private String invoiceType;

    private Date invoiceDate;

    private List<Pair> pair;

    private String employeeCode;

    private String employeeName;

    private String vehicleNo;

    private Date deliveryDate;

    private double grossTotal;

    private double discountedTotal;

    private String deliveryDoneBy;

    private String creditLimit;

    private String paymentMethod;

    private String chequeNo;


    public InvoiceResponse(String customerCode, String customerName, String customerAddress, long orderId, long invoiceId, String invoiceType, Date invoiceDate, List<Pair> pair, String employeeCode, String employeeName, String vehicleNo, Date deliveryDate, double grossTotal, double discountedTotal, String deliveryDoneBy, String creditLimit, String paymentMethod, String chequeNo) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderId = orderId;
        this.invoiceId = invoiceId;
        this.invoiceType = invoiceType;
        this.invoiceDate = invoiceDate;
        this.pair = pair;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.vehicleNo = vehicleNo;
        this.deliveryDate = deliveryDate;
        this.grossTotal = grossTotal;
        this.discountedTotal = discountedTotal;
        this.deliveryDoneBy = deliveryDoneBy;
        this.creditLimit = creditLimit;
        this.paymentMethod = paymentMethod;
        this.chequeNo = chequeNo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<Pair> getPair() {
        return pair;
    }

    public void setPair(List<Pair> pair) {
        this.pair = pair;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public double getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(double discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public String getDeliveryDoneBy() {
        return deliveryDoneBy;
    }

    public void setDeliveryDoneBy(String deliveryDoneBy) {
        this.deliveryDoneBy = deliveryDoneBy;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }
}
