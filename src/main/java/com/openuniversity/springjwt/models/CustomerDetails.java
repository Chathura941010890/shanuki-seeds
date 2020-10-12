package com.openuniversity.springjwt.models;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer_details")
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "customercode")
    private String customerCode;

    @NotBlank
    @Column(name = "customername")
    private String customerName;

    @NotBlank
    @Email
    @Column(name = "customeremail")
    private String customerEmail;

    @NotBlank
    @Column(name = "customeraddress")
    private String customerAddress;

    @NotBlank
    @Column(name = "customercontactnumber")
    private String customerContactNumber;

    @NotBlank
    @Column(name = "customerstatus")
    private String customerStatus;

    public CustomerDetails(){

    }

    public CustomerDetails( String customer_code, String customer_name, String customer_address, String customer_email, String customer_contact_number, String customerStatus){
       // this.id = id;
        this.customerCode = customer_code;
        this.customerName = customer_name;
        this.customerAddress = customer_address;
        this.customerEmail = customer_email;
        this.customerContactNumber = customer_contact_number;
        this.customerStatus = customerStatus;
        setCustomer_code(customer_code);
        setCustomer_name(customer_name);
        setCustomer_address(customer_address);
        setCustomer_email(customer_email);
        setCustomer_contact_number(customer_contact_number);
        setCustomerStatus(customerStatus);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer_code() {
        return customerCode;
    }

    public void setCustomer_code(String customer_code) {
        this.customerCode = customer_code;
    }

    public String getCustomer_name() {
        return customerName;
    }

    public void setCustomer_name(String customer_name) {
        this.customerName = customer_name;
    }

    public String getCustomer_email() {
        return customerEmail;
    }

    public void setCustomer_email(String customer_email) {
        this.customerEmail = customer_email;
    }

    public String getCustomer_address() {
        return customerAddress;
    }

    public void setCustomer_address(String customer_address) {
        this.customerAddress = customer_address;
    }

    public String getCustomer_contact_number() {
        return customerContactNumber;
    }

    public void setCustomer_contact_number(String customer_contact_number) {
        this.customerContactNumber = customer_contact_number;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }
}
