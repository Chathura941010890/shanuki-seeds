package com.openuniversity.springjwt.payload.request;

import com.openuniversity.springjwt.models.Bundle;
import com.openuniversity.springjwt.models.ProductDetails;

public class ProductBundleSizesPriceQuantityRequest {
    ProductDetails productDetails;
    Bundle bundle;
    long price;
   // long quantity;

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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
