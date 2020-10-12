package com.openuniversity.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "productCode")
    private String productCode;

    @NotBlank
    @Column(name = "productName")
    private String productName;

    public ProductDetails(){

    }

    public ProductDetails(String productCode, String productName){
        this.productCode = productCode;
        this.productName = productName;
        setProductCode(productCode);
        setProductName(productName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
