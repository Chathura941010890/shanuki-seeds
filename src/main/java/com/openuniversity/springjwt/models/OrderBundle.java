package com.openuniversity.springjwt.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "order_bundle")
public class OrderBundle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
   // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private BasicOrderDetails basicOrderDetails;

    @NotBlank
   // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_bundleSize_id")
    private  ProductBundleSizesPricesQuantity productBundleSizesPricesQuantities;

    @NotBlank
    @Column(name = "quantity")
    private long quantity;

    public OrderBundle(){

    }

    public OrderBundle(@NotBlank BasicOrderDetails basicOrderDetails, @NotBlank ProductBundleSizesPricesQuantity productBundleSizesPricesQuantities, @NotBlank long quantity) {
        this.basicOrderDetails = basicOrderDetails;
        this.productBundleSizesPricesQuantities = productBundleSizesPricesQuantities;
        this.quantity = quantity;
        setBasicOrderDetails(basicOrderDetails);
        setProductBundleSizesPricesQuantities(productBundleSizesPricesQuantities);
        setQuantity(quantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BasicOrderDetails getBasicOrderDetails() {
        return basicOrderDetails;
    }

    public void setBasicOrderDetails(BasicOrderDetails basicOrderDetails) {
        this.basicOrderDetails = basicOrderDetails;
    }

    public ProductBundleSizesPricesQuantity getProductBundleSizesPricesQuantities() {
        return productBundleSizesPricesQuantities;
    }

    public void setProductBundleSizesPricesQuantities(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantities) {
        this.productBundleSizesPricesQuantities = productBundleSizesPricesQuantities;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
