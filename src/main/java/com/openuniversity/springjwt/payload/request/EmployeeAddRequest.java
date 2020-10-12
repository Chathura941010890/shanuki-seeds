package com.openuniversity.springjwt.payload.request;

import com.openuniversity.springjwt.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmployeeAddRequest {
    @NotBlank
    private String employeeCode;

    @NotBlank
    private String employeeName;

    @NotBlank
    private String employeeAddress;

    @NotBlank
    @Email
    private String employeeEmail;

    @NotBlank
    private String employeeContact;

    @NotBlank
    private User user;

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

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
