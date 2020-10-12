package com.openuniversity.springjwt.payload.request;

public class PacketSizeDetailsRequest {
    private String sizeName;

    private String packetWeight;

    public String getSizeName() {
        return sizeName;
    }

    public String getPacketWeight() {
        return packetWeight;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public void setPacketWeight(String packetWeight) {
        this.packetWeight = packetWeight;
    }
}
