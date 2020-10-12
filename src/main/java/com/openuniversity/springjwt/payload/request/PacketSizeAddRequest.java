package com.openuniversity.springjwt.payload.request;

public class PacketSizeAddRequest {
    private String sizeName;

    private String packetWeight;


    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public void setPacketWeight(String packetWeight) {
        this.packetWeight = packetWeight;
    }

    public String getSizeName() {
        return sizeName;
    }

    public String getPacketWeight() {
        return packetWeight;
    }
}
