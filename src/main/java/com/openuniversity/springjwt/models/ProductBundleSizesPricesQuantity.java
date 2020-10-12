package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_bundleSizes_price_quantity")
@EntityListeners(AuditingEntityListener.class)
public class ProductBundleSizesPricesQuantity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductDetails productDetails;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bundleSize_id")
    private Bundle bundle;

    @NotBlank
    @Column(name = "bundle_price")
    private long bundlePrice;

//    @NotBlank
//    @Column(name = "bundleQuantity")
//    private long bundleQuantity;

    public ProductBundleSizesPricesQuantity(){

    }

    public ProductBundleSizesPricesQuantity(ProductDetails productDetails, Bundle bundle, long bundlePrice){
        this.productDetails = productDetails;
        this.bundle = bundle;
        this.bundlePrice =  bundlePrice;
        //this.bundleQuantity = bundleQuantity;
        setProductDetails(productDetails);
        setBundle(bundle);
        setBundlePrice(bundlePrice);
       // setBundleQuantity(bundleQuantity);
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

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public long getBundlePrice() {
        return bundlePrice;
    }

    public void setBundlePrice(long bundlePrice) {
        this.bundlePrice = bundlePrice;
    }

//    public long getBundleQuantity() {
//        return bundleQuantity;
//    }
//
//    public void setBundleQuantity(long bundleQuantity) {
//        this.bundleQuantity = bundleQuantity;
//    }
}
