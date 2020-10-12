package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductSupplierQuantity;

public class PSQPacketQuantityResponse {
    private ProductSupplierQuantity productSupplierQuantity;

    private PacketSizes packetSizes;

    private long quantity;

    public PSQPacketQuantityResponse(ProductSupplierQuantity productSupplierQuantity, PacketSizes packetSizes, long quantity) {
        this.productSupplierQuantity = productSupplierQuantity;
        this.packetSizes = packetSizes;
        this.quantity = quantity;
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
