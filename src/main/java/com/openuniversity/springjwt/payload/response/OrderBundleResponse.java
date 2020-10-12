package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.ProductBundleSizesPricesQuantity;

import java.util.List;

public class OrderBundleResponse {
    ProductBundleSizesPricesQuantity productBundleSizesPricesQuantities;

    Long quantity;

    public OrderBundleResponse(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantities, Long quantity) {
        this.productBundleSizesPricesQuantities = productBundleSizesPricesQuantities;
        this.quantity = quantity;
    }

    public ProductBundleSizesPricesQuantity getProductBundleSizesPricesQuantities() {
        return productBundleSizesPricesQuantities;
    }

    public void setProductBundleSizesPricesQuantities(ProductBundleSizesPricesQuantity productBundleSizesPricesQuantities) {
        this.productBundleSizesPricesQuantities = productBundleSizesPricesQuantities;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
