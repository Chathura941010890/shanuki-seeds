package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "secondary_invoice_details")
@EntityListeners(AuditingEntityListener.class)
public class SecondaryInvoiceDetails extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    private BasicInvoiceDetails basicInvoiceDetails;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderPacket_id")
    private OrderPackets orderPackets;

    @NotBlank
    @Column(name = "discount")
    private double discount;

    @NotBlank
    @Column(name = "grossValue")
    private double grossValue;

    @NotBlank
    @Column(name = "discountedValu")
    private double discountedValue;

    public SecondaryInvoiceDetails(@NotBlank BasicInvoiceDetails basicInvoiceDetails, @NotBlank OrderPackets orderPackets, @NotBlank double discount, @NotBlank double grossValue, @NotBlank double discountedValue) {
        this.basicInvoiceDetails = basicInvoiceDetails;
        this.orderPackets = orderPackets;
        this.discount = discount;
        this.grossValue = grossValue;
        this.discountedValue = discountedValue;
        setBasicInvoiceDetails(basicInvoiceDetails);
        setOrderPackets(orderPackets);
        setDiscount(discount);
        setGrossValue(grossValue);
        setDiscountedValue(discountedValue);
    }

    public SecondaryInvoiceDetails(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(double grossValue) {
        this.grossValue = grossValue;
    }

    public double getDiscountedValue() {
        return discountedValue;
    }

    public void setDiscountedValue(double discountedValue) {
        this.discountedValue = discountedValue;
    }
}
