package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.ProductPacketSizesPriceQuantity;

public class PPPQuantityResponse {
    private ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity;

    private long quantity;

    public ProductPacketSizesPriceQuantity getProductPacketSizesPriceQuantity() {
        return productPacketSizesPriceQuantity;
    }

    public void setProductPacketSizesPriceQuantity(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity) {
        this.productPacketSizesPriceQuantity = productPacketSizesPriceQuantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public PPPQuantityResponse(ProductPacketSizesPriceQuantity productPacketSizesPriceQuantity, long quantity) {
        this.productPacketSizesPriceQuantity = productPacketSizesPriceQuantity;
        this.quantity = quantity;


    }
}
