package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.Bundle;
import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductDetails;

public class QuantityByProductResponse {

    ProductDetails productDetails;
    PacketSizes packetSizes;
    //Bundle bundle;
    long quantity;

    public QuantityByProductResponse(ProductDetails productDetails, PacketSizes packetSizes, long quantity) {
        this.productDetails = productDetails;
        this.packetSizes = packetSizes;
        //this.bundle = bundle;
        this.quantity = quantity;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
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

//    public Bundle getBundle() {
//        return bundle;
//    }
//
//    public void setBundle(Bundle bundle) {
//        this.bundle = bundle;
//    }
}
