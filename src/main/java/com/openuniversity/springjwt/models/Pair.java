package com.openuniversity.springjwt.models;

public class Pair {
    String productCode;
    String productName;
    String packetWeight;
    long packetQuantity;
    double unitPrice;
    double eachDiscount;
    double eachGrossValue;
    double eachDiscountedValue;

    public Pair(String productCode, String productName, String packetWeight, long packetQuantity, double unitPrice, double eachDiscount, double eachGrossValue, double eachDiscountedValue) {
        this.productCode = productCode;
        this.productName = productName;
        this.packetWeight = packetWeight;
        this.packetQuantity = packetQuantity;
        this.unitPrice = unitPrice;
        this.eachDiscount = eachDiscount;
        this.eachGrossValue = eachGrossValue;
        this.eachDiscountedValue = eachDiscountedValue;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPacketWeight() {
        return packetWeight;
    }

    public void setPacketWeight(String packetWeight) {
        this.packetWeight = packetWeight;
    }

    public long getPacketQuantity() {
        return packetQuantity;
    }

    public void setPacketQuantity(long packetQuantity) {
        this.packetQuantity = packetQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getEachDiscount() {
        return eachDiscount;
    }

    public void setEachDiscount(double eachDiscount) {
        this.eachDiscount = eachDiscount;
    }

    public double getEachGrossValue() {
        return eachGrossValue;
    }

    public void setEachGrossValue(double eachGrossValue) {
        this.eachGrossValue = eachGrossValue;
    }

    public double getEachDiscountedValue() {
        return eachDiscountedValue;
    }

    public void setEachDiscountedValue(double eachDiscountedValue) {
        this.eachDiscountedValue = eachDiscountedValue;
    }
}
