package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_supplier_quantity_packet_quantity")
@EntityListeners(AuditingEntityListener.class)
public class PSQPacketQuantity extends Auditable<String> {
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
    @JoinColumn(name = "packetSize_id")
    private PacketSizes packetSizes;

    @NotBlank
    @Column(name = "quantity")
    private long quantity;

    public PSQPacketQuantity(){

    }

    public PSQPacketQuantity(@NotBlank ProductSupplierQuantity productSupplierQuantity, @NotBlank PacketSizes packetSizes, @NotBlank long quantity) {
        this.productSupplierQuantity = productSupplierQuantity;
        this.packetSizes = packetSizes;
        this.quantity = quantity;
        setProductSupplierQuantity(productSupplierQuantity);
        setPacketSizes(packetSizes);
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

    public PacketSizes getPacketSizes() {
        return packetSizes;
    }

    public void setPacketSizes(PacketSizes packetSizes) {
        this.packetSizes = packetSizes;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
