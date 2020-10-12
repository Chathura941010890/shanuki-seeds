package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ppp_quantity")
@EntityListeners(AuditingEntityListener.class)
public class PPPQuantity extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ppp_id")
    private ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity;

    @NotBlank
    @Column(name = "packetQuantity")
    private long packetQuantity;

    public PPPQuantity(){

    }

    public PPPQuantity(@NotBlank ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity, @NotBlank long packetQuantity) {
        this.productPacketSizesPriceQuantity = productPacketSizesPriceQuantity;
        this.packetQuantity = packetQuantity;
        setPacketQuantity(packetQuantity);
        setProductPacketSizesPriceQuantity(productPacketSizesPriceQuantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductPacketSizesPriceQuantity getProductPacketSizesPriceQuantity() {
        return productPacketSizesPriceQuantity;
    }

    public void setProductPacketSizesPriceQuantity(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity) {
        this.productPacketSizesPriceQuantity = productPacketSizesPriceQuantity;
    }

    public long getPacketQuantity() {
        return packetQuantity;
    }

    public void setPacketQuantity(long packetQuantity) {
        this.packetQuantity = packetQuantity;
    }
}
