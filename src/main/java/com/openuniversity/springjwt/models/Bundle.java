package com.openuniversity.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bundle_details")
public class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Column(name = "bundleName")
    private String bundleName;

    @NotBlank
    @Column(name = "packetQuantity")
    private long packetQuantity;

    public Bundle(){

    }

    public Bundle(String bundleName, long packetQuantity){
        this.bundleName = bundleName;
        this.packetQuantity = packetQuantity;
        setBundleName(bundleName);
        setPacketQuantity(packetQuantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
