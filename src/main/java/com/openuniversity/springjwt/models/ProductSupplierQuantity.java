package com.openuniversity.springjwt.models;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_supplier_quantity")
@EntityListeners(AuditingEntityListener.class)
public class ProductSupplierQuantity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Column(name = "lot_no")
    private String lotNo;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductDetails productDetails;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private SupplierDetails supplierDetails;

    @NotBlank
    @Column(name = "quantity")
    private long quantity;

    @NotBlank
    @Column(name = "price")
    private long price;

    @NotBlank
    @Column(name = "what_happend_to_this")
    private boolean added;

    public ProductSupplierQuantity(@NotBlank String lotNo, @NotBlank ProductDetails productDetails, @NotBlank SupplierDetails supplierDetails, @NotBlank long quantity, @NotBlank long price, @NotBlank boolean added) {
        this.productDetails = productDetails;
        this.supplierDetails = supplierDetails;
        this.quantity = quantity;
        this.price = price;
        this.added = added;
        this.lotNo = lotNo;
        setLotNo(lotNo);
        setProductDetails(productDetails);
        setSupplierDetails(supplierDetails);
        setQuantity(quantity);
        setPrice(price);
        setAdded(added);
    }

    public ProductSupplierQuantity(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public SupplierDetails getSupplierDetails() {
        return supplierDetails;
    }

    public void setSupplierDetails(SupplierDetails supplierDetails) {
        this.supplierDetails = supplierDetails;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean getAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

}
