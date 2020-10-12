package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.Bundle;
import com.openuniversity.springjwt.models.ProductSupplierQuantity;

public class PSQBundleQuantityResponse {
    private ProductSupplierQuantity productSupplierQuantity;

    private Bundle bundle;

    private long quantity;

    public PSQBundleQuantityResponse(ProductSupplierQuantity productSupplierQuantity, Bundle bundle, long quantity) {
        this.productSupplierQuantity = productSupplierQuantity;
        this.bundle = bundle;
        this.quantity = quantity;
    }

    public ProductSupplierQuantity getProductSupplierQuantity() {
        return productSupplierQuantity;
    }

    public void setProductSupplierQuantity(ProductSupplierQuantity productSupplierQuantity) {
        this.productSupplierQuantity = productSupplierQuantity;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
