package com.openuniversity.springjwt.payload.request;

import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductDetails;

public class ProductPacketSizesPriceQuantityRequest {
    ProductDetails productDetails;
    PacketSizes packetSizes;
    double price;
    //long quantity;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(long quantity) {
//        this.quantity = quantity;
//    }
}
