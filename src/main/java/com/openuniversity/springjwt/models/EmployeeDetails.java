package com.openuniversity.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "employeecode")
    private String employeeCode;


    @NotBlank
    @Column(name = "employeename")
    private String employeeName;

    @NotBlank
    @Email
    @Column(name = "employeeemail")
    private String employeeEmail;

    @NotBlank
    @Column(name = "employeeaddress")
    private String employeeAddress;

    @NotBlank
    @Column(name = "employeecontactnumber")
    private String employeeContactNumber;

    @NotBlank
    @Column(name = "employeestatus")
    private String employeeStatus;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public EmployeeDetails(){

    }

    public EmployeeDetails( String employee_code, String employee_name, String employee_address, String employee_email, String employee_contact_number, String employeeStatus, User user){
        // this.id = id;
        this.employeeCode = employee_code;
        this.employeeName = employee_name;
        this.employeeAddress = employee_address;
        this.employeeEmail = employee_email;
        this.employeeContactNumber = employee_contact_number;
        this.employeeStatus = employeeStatus;
        this.user = user;
        setEmployeeCode(employee_code);
        setEmployeeName(employee_name);
        setEmployeeAddress(employee_address);
        setEmployeeEmail(employee_email);
        setEmployeeContactNumber(employee_contact_number);
        setEmployeeStatus(employeeStatus);
        setUser(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeContactNumber() {
        return employeeContactNumber;
    }

    public void setEmployeeContactNumber(String employeeContactNumber) {
        this.employeeContactNumber = employeeContactNumber;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
