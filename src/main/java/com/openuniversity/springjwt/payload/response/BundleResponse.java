package com.openuniversity.springjwt.payload.response;

public class BundleResponse {
    private String bundleName;

    private long packetQuantity;

    public BundleResponse(String bundleName, long packetQuantity){
        this.bundleName = bundleName;
        this.packetQuantity = packetQuantity;
    }

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
