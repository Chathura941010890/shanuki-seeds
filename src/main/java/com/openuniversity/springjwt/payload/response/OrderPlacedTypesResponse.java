package com.openuniversity.springjwt.payload.response;

public class OrderPlacedTypesResponse {
    String type;

    public OrderPlacedTypesResponse(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
