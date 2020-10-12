package com.openuniversity.springjwt.payload.request;

import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductSupplierQuantity;

public class PSQPacketQuantityRequest {
    private ProductSupplierQuantity productSupplierQuantity;

    private PacketSizes packetSizes;

    private long quantity;

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
