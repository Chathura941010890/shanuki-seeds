package com.openuniversity.springjwt.payload.response;


import java.util.List;

public class SupplierDetailsResponse {
    private String code;
    private String name;
    private String address;
    private String email;
    private String contact;

    public SupplierDetailsResponse(String code, String name, String address, String email, String contact){
        this.code = code;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
