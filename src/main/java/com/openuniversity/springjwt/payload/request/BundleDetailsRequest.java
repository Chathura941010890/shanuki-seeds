package com.openuniversity.springjwt.payload.request;

public class BundleDetailsRequest {
    private String bundleName;

    private long packetQuantity;

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public long getPacketQuantity() {
        return packetQuantity;
    }

    public void setPacketQuantity(long packetQuantity) {
        this.packetQuantity = packetQuantity;
    }
}
