package com.openuniversity.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "company_details")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "address")
    private String address;

    @NotBlank
    @Column(name = "reg_no")
    private String reg_no;

    @NotBlank
    @Column(name = "hotline")
    private String hotline;

    @NotBlank
    @Column(name = "mobile_no")
    private String mobile;

    @NotBlank
    @Column(name = "fax_no")
    private String fax_no;

    @NotBlank
    @Column(name = "owner")
    private String owner;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "description")
    private String description;

    public Company(@NotBlank String name, @NotBlank String address, @NotBlank String reg_no, @NotBlank String hotline, @NotBlank String mobile, @NotBlank String fax_no, @NotBlank String owner, @NotBlank String email, @NotBlank String description) {
        this.name = name;
        this.address = address;
        this.reg_no = reg_no;
        this.hotline = hotline;
        this.mobile = mobile;
        this.fax_no = fax_no;
        this.owner = owner;
        this.email = email;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax_no() {
        return fax_no;
    }

    public void setFax_no(String fax_no) {
        this.fax_no = fax_no;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
