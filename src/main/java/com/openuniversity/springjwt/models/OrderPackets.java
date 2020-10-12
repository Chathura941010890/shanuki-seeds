package com.openuniversity.springjwt.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "order_packets")
public class OrderPackets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private BasicOrderDetails basicOrderDetails;

    @NotBlank
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_packetSize_id")
    private  ProductPacketSizesPriceQuantity productPacketSizesPriceQuantities;

    @NotBlank
    @Column(name = "quantity")
    private long quantity;


    public OrderPackets(){

    }

    public OrderPackets(@NotBlank BasicOrderDetails basicOrderDetails, @NotBlank ProductPacketSizesPriceQuantity productPacketSizesPriceQuantities, @NotBlank long quantity) {
        this.basicOrderDetails = basicOrderDetails;
        this.productPacketSizesPriceQuantities = productPacketSizesPriceQuantities;
        this.quantity = quantity;
        setBasicOrderDetails(basicOrderDetails);
        setProductPacketSizesPriceQuantities(productPacketSizesPriceQuantities);
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

    public ProductPacketSizesPriceQuantity getProductPacketSizesPriceQuantities() {
        return productPacketSizesPriceQuantities;
    }

    public void setProductPacketSizesPriceQuantities(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantities) {
        this.productPacketSizesPriceQuantities = productPacketSizesPriceQuantities;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
