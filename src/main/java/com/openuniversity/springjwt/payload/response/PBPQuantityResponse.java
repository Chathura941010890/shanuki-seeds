package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.ProductBundleSizesPricesQuantity;

public class PBPQuantityResponse {
    private ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity;

    private long quantity;

    public ProductBundleSizesPricesQuantity getProductBundleSizesPricesQuantity() {
        return productBundleSizesPricesQuantity;
    }

    public void setProductBundleSizesPricesQuantity(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity) {
        this.productBundleSizesPricesQuantity = productBundleSizesPricesQuantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public PBPQuantityResponse(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantity, long quantity) {
        this.productBundleSizesPricesQuantity = productBundleSizesPricesQuantity;
        this.quantity = quantity;


    }
}
