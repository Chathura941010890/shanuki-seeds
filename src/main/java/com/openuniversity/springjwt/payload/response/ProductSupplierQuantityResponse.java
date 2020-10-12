package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.ProductDetails;
import com.openuniversity.springjwt.models.SupplierDetails;

public class ProductSupplierQuantityResponse {
    private ProductDetails productDetails;

    private SupplierDetails supplierDetails;

    private long quantity;

    private long price;

    private boolean added;

    public ProductSupplierQuantityResponse(ProductDetails productDetails, SupplierDetails supplierDetails, long quantity, long price, boolean added) {
        this.productDetails = productDetails;
        this.supplierDetails = supplierDetails;
        this.quantity = quantity;
        this.price = price;
        this.added = added;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public SupplierDetails getSupplierDetails() {
        return supplierDetails;
    }

    public void setSupplierDetails(SupplierDetails supplierDetails) {
        this.supplierDetails = supplierDetails;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean getAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
}
