package com.openuniversity.springjwt.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "packet_sizes")
public class PacketSizes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "sizeName")
    private String sizeName;

    @NotBlank
    @Column(name = "packetWeight")
    private String packetWeight;

    public PacketSizes(){

    }

    public PacketSizes(String sizeName, String packetWeight){
        this.sizeName = sizeName;
        this.packetWeight = packetWeight;
        setSizeName(sizeName);
        setPacketWeight(packetWeight);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
