package com.openuniversity.springjwt.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SupplierAddRequest {
    @NotBlank
    private String supplierCode;

    @NotBlank
    private String supplierName;

    @NotBlank
    private String supplierAddress;

    @NotBlank
    @Email
    private String supplierEmail;

    @NotBlank
    private String supplierContact;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }
}
