package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.models.ProductDetails;

public class ProductPacketSizesPriceQuantityResponse {

        ProductDetails productDetails;
        PacketSizes packetSizes;
        double price;
       // long quantity;

        public ProductPacketSizesPriceQuantityResponse(ProductDetails productDetails, PacketSizes packetSizes, double price){
            this.packetSizes = packetSizes;
            this.productDetails = productDetails;
            this.price = price;
            //this.quantity = quantity;
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
