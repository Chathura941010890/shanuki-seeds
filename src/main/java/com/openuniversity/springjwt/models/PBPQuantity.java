package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pbp_quantity")
@EntityListeners(AuditingEntityListener.class)
public class PBPQuantity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pbp_id")
    private ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity;

    @NotBlank
    @Column(name = "bundleQuantity")
    private long bundleQuantity;

    public PBPQuantity(){

    }

    public PBPQuantity(@NotBlank ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity, @NotBlank long bundleQuantity) {
        this.productBundleSizesPricesQuantity = productBundleSizesPricesQuantity;
        this.bundleQuantity = bundleQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductBundleSizesPricesQuantity getProductBundleSizesPricesQuantity() {
        return productBundleSizesPricesQuantity;
    }

    public void setProductBundleSizesPricesQuantity(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity) {
        this.productBundleSizesPricesQuantity = productBundleSizesPricesQuantity;
    }

    public long getBundleQuantity() {
        return bundleQuantity;
    }

    public void setBundleQuantity(long bundleQuantity) {
        this.bundleQuantity = bundleQuantity;
    }
}
