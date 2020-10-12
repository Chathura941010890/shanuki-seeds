package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.ProductDetails;
import com.openuniversity.springjwt.models.ProductPacketSizesPriceQuantity;

import java.util.List;

public class OrderPacketResponse {

    ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity;

    Long quantity;

    public OrderPacketResponse(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity, Long quantity) {
        this.productPacketSizesPriceQuantity = productPacketSizesPriceQuantity;
        this.quantity = quantity;
    }

    public ProductPacketSizesPriceQuantity getProductPacketSizesPriceQuantity() {
        return productPacketSizesPriceQuantity;
    }

    public void setProductPacketSizesPriceQuantity(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity) {
        this.productPacketSizesPriceQuantity = productPacketSizesPriceQuantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
