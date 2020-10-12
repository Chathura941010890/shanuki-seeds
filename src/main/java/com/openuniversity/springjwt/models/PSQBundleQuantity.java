package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_supplier_quantity_bundle_quantity")
@EntityListeners(AuditingEntityListener.class)
public class PSQBundleQuantity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PSQ_id")
    private ProductSupplierQuantity productSupplierQuantity;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bundle_id")
    private Bundle bundle;

    @NotBlank
    @Column(name = "quantity")
    private long quantity;

    public PSQBundleQuantity(){

    }

    public PSQBundleQuantity(@NotBlank ProductSupplierQuantity productSupplierQuantity, @NotBlank Bundle bundle, @NotBlank long quantity) {
        this.productSupplierQuantity = productSupplierQuantity;
        this.bundle = bundle;
        this.quantity = quantity;
        setProductSupplierQuantity(productSupplierQuantity);
        setBundle(bundle);
        setQuantity(quantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductSupplierQuantity getProductSupplierQuantity() {
        return productSupplierQuantity;
    }

    public void setProductSupplierQuantity(ProductSupplierQuantity productSupplierQuantity) {
        this.productSupplierQuantity = productSupplierQuantity;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}