package com.openuniversity.springjwt.payload.response;

public class PacketSizeResponse {
    private String sizeName;

    private String packetWeight;

    public PacketSizeResponse(String sizeName, String packetWeight){
        this.sizeName = sizeName;
        this.packetWeight = packetWeight;
    }

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
