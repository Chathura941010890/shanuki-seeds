package com.openuniversity.springjwt.payload.response;


import com.openuniversity.springjwt.models.User;

import java.util.List;

public class EmployeeDetailsResponse {
    private String code;
    private String name;
    private String address;
    private String email;
    private String contact;
    private User user;

    public EmployeeDetailsResponse(String code, String name, String address, String email, String contact, User user){
        this.code = code;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
