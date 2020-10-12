package com.openuniversity.springjwt.payload.request;

import com.openuniversity.springjwt.models.BasicInvoiceDetails;
import com.openuniversity.springjwt.models.OrderPackets;

public class SecondaryInvoiceDetailsRequest {
    private BasicInvoiceDetails basicInvoiceDetails;
    private OrderPackets orderPackets;
    private long discount;
    private long grossValue;
    private long discountedValue;

    public BasicInvoiceDetails getBasicInvoiceDetails() {
        return basicInvoiceDetails;
    }

    public void setBasicInvoiceDetails(BasicInvoiceDetails basicInvoiceDetails) {
        this.basicInvoiceDetails = basicInvoiceDetails;
    }

    public OrderPackets getOrderPackets() {
        return orderPackets;
    }

    public void setOrderPackets(OrderPackets orderPackets) {
        this.orderPackets = orderPackets;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public long getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(long grossValue) {
        this.grossValue = grossValue;
    }

    public long getDiscountedValue() {
        return discountedValue;
    }

    public void setDiscountedValue(long discountedValue) {
        this.discountedValue = discountedValue;
    }
}
