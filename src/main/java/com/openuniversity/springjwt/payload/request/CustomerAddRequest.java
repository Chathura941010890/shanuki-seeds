package com.openuniversity.springjwt.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerAddRequest {
    @NotBlank
    private String customerCode;

    @NotBlank
    private String customerName;

    @NotBlank
    private String customerAddress;

    @NotBlank
    @Email
    private String customerEmail;

    @NotBlank
    private String customerContact;

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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }
}
