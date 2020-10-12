package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "basic_invoice_details")
@EntityListeners(AuditingEntityListener.class)
public class BasicInvoiceDetails extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private BasicOrderDetails basicOrderDetails;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_type")
    private InvoiceTypes invoiceTypes;

    @NotBlank
    @Column(name = "payment_method")
    private String paymentMethod;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "invoiced_by")
    private EmployeeDetails employeeDetails;

    @Column(name = "gross_total")
    private double grossTotal;

    @Column(name = "discounted_total")
    private double discountedTotal;

    @Column(name = "final_total")
    private double finalTotal;

    @Column(name = "credit_limit")
    private String creditLimit;

    @Column(name = "cheque_no")
    private String chequeNumber;



    public BasicInvoiceDetails(@NotBlank BasicOrderDetails basicOrderDetails, @NotBlank InvoiceTypes invoiceTypes, String paymentMethod, EmployeeDetails employeeDetails, double grossTotal, double discountedTotal, double finalTotal, String creditLimit, String chequeNumber) {
        this.basicOrderDetails = basicOrderDetails;
        this.invoiceTypes = invoiceTypes;
        this.paymentMethod = paymentMethod;
        this.employeeDetails = employeeDetails;
        this.grossTotal = grossTotal;
        this.discountedTotal = discountedTotal;
        this.finalTotal = finalTotal;
        this.creditLimit = creditLimit;
        this.chequeNumber = chequeNumber;
        setBasicOrderDetails(basicOrderDetails);
        setInvoiceTypes(invoiceTypes);
        setPaymentMethod(paymentMethod);
        setEmployeeDetails(employeeDetails);
        setGrossTotal(grossTotal);
        setDiscountedTotal(discountedTotal);
        setFinalTotal(finalTotal);
        setCreditLimit(creditLimit);
        setChequeNumber(chequeNumber);
    }

    public BasicInvoiceDetails(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }
}
