package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.PacketSizes;

public class PacketSizesForAProductResponse {
    private PacketSizes packetSizes;

    public PacketSizesForAProductResponse(PacketSizes packetSizes) {
        this.packetSizes = packetSizes;
    }

    public PacketSizes getPacketSizes() {
        return packetSizes;
    }

    public void setPacketSizes(PacketSizes packetSizes) {
        this.packetSizes = packetSizes;
    }
}
