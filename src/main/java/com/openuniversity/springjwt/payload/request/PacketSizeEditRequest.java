package com.openuniversity.springjwt.payload.request;

public class PacketSizeEditRequest {
    private String sizeName;

    private String packetWeight;

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getPacketWeight() {
        return packetWeight;
    }

    public void setPacketWeight(String packetWeight) {
        this.packetWeight = packetWeight;
    }
}
