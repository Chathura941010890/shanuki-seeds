package com.openuniversity.springjwt.models;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_packetSizes_price_quantity")
@EntityListeners(AuditingEntityListener.class)
public class ProductPacketSizesPriceQuantity extends Auditable<String> {
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
    @JoinColumn(name = "packetSize_id")
    private PacketSizes packetSizes;

    @NotBlank
    @Column(name = "packet_price")
    private double packetPrice;

//    @NotBlank
//    @Column(name = "packetQuantity")
//    private long packetQuantity;

    public ProductPacketSizesPriceQuantity(){

    }

    public ProductPacketSizesPriceQuantity(ProductDetails productDetails, PacketSizes packetSizes, double packetPrice){
        this.productDetails = productDetails;
        this.packetSizes = packetSizes;
        this.packetPrice =  packetPrice;
        //this.packetQuantity = packetQuantity;
        setProductId(productDetails);
        setPacketSizes(packetSizes);
        setPacketPrice(packetPrice);
        //setPacketQuantity(packetQuantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDetails getProductId() {
        return productDetails;
    }

    public void setProductId(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public PacketSizes getPacketSizes() {
        return packetSizes;
    }

    public void setPacketSizes(PacketSizes packetSizes) {
        this.packetSizes = packetSizes;
    }

    public double getPacketPrice() {
        return packetPrice;
    }

    public void setPacketPrice(double packetPrice) {
        this.packetPrice = packetPrice;
    }

//    public long getPacketQuantity() {
//        return packetQuantity;
//    }
//
//    public void setPacketQuantity(long packetQuantity) {
//        this.packetQuantity = packetQuantity;
//    }
}
