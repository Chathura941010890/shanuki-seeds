package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.Bundle;

public class BundleSizesForAProductResponse {
    Bundle bundle;

    public BundleSizesForAProductResponse(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
