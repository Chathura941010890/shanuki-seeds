package com.openuniversity.springjwt.models;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "supplier_details")
public class SupplierDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "suppliercode")
    private String supplierCode;

    @NotBlank
    @Column(name = "suppliername")
    private String supplierName;

    @NotBlank
    @Email
    @Column(name = "supplieremail")
    private String supplierEmail;

    @NotBlank
    @Column(name = "supplieraddress")
    private String supplierAddress;

    @NotBlank
    @Column(name = "suppliercontactnumber")
    private String supplierContactNumber;

    @NotBlank
    @Column(name = "supplierstatus")
    private String supplierStatus;

    public SupplierDetails(){

    }

    public SupplierDetails( String supplier_code, String supplier_name, String supplier_address, String supplier_email, String supplier_contact_number, String supplierStatus){
        // this.id = id;
        this.supplierCode = supplier_code;
        this.supplierName = supplier_name;
        this.supplierAddress = supplier_address;
        this.supplierEmail = supplier_email;
        this.supplierContactNumber = supplier_contact_number;
        this.supplierStatus = supplierStatus;
        setSupplier_code(supplier_code);
        setSupplier_name(supplier_name);
        setSupplier_address(supplier_address);
        setSupplier_email(supplier_email);
        setSupplier_contact_number(supplier_contact_number);
        setSupplierStatus(supplierStatus);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSupplier_code() {
        return supplierCode;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplierCode = supplier_code;
    }

    public String getSupplier_name() {
        return supplierName;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplierName = supplier_name;
    }

    public String getSupplier_email() {
        return supplierEmail;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplierEmail = supplier_email;
    }

    public String getSupplier_address() {
        return supplierAddress;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplierAddress = supplier_address;
    }

    public String getSupplier_contact_number() {
        return supplierContactNumber;
    }

    public void setSupplier_contact_number(String supplier_contact_number) {
        this.supplierContactNumber = supplier_contact_number;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }
}
