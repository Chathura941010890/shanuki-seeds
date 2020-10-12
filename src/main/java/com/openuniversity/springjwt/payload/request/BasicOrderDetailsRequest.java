package com.openuniversity.springjwt.payload.request;

import com.openuniversity.springjwt.models.*;

import java.util.Date;
import java.util.List;

public class BasicOrderDetailsRequest {
    CustomerDetails customerDetails;
    Date deadline;
    boolean confirmed;
    boolean placed;
    Date orderPlacedDate;
    EmployeeDetails orderPlacedEmployee;
    OrderPlacedTypes orderPlacedTypes;
    String vehicleNo;
    boolean invoiced;
    List<ProductPacketSizesPriceQuantity> productPacketSizesPriceQuantity;
    List <Long> packetQuantity;
    List <ProductBundleSizesPricesQuantity> productBundleSizesPricesQuantity;
    List <Long> bundleQuantity;


    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public Date getOrderPlacedDate() {
        return orderPlacedDate;
    }

    public void setOrderPlacedDate(Date orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }

    public EmployeeDetails getOrderPlacedEmployee() {
        return orderPlacedEmployee;
    }

    public void setOrderPlacedEmployee(EmployeeDetails orderPlacedEmployee) {
        this.orderPlacedEmployee = orderPlacedEmployee;
    }

    public OrderPlacedTypes getOrderPlacedTypes() {
        return orderPlacedTypes;
    }

    public void setOrderPlacedTypes(OrderPlacedTypes orderPlacedTypes) {
        this.orderPlacedTypes = orderPlacedTypes;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public List<ProductPacketSizesPriceQuantity> getProductPacketSizesPriceQuantity() {
        return productPacketSizesPriceQuantity;
    }

    public void setProductPacketSizesPriceQuantity(List<ProductPacketSizesPriceQuantity> productPacketSizesPriceQuantity) {
        this.productPacketSizesPriceQuantity = productPacketSizesPriceQuantity;
    }

    public List<Long> getPacketQuantity() {
        return packetQuantity;
    }

    public void setPacketQuantity(List<Long> packetQuantity) {
        this.packetQuantity = packetQuantity;
    }

    public List<ProductBundleSizesPricesQuantity> getProductBundleSizesPricesQuantity() {
        return productBundleSizesPricesQuantity;
    }

    public void setProductBundleSizesPricesQuantity(List<ProductBundleSizesPricesQuantity> productBundleSizesPricesQuantity) {
        this.productBundleSizesPricesQuantity = productBundleSizesPricesQuantity;
    }

    public List<Long> getBundleQuantity() {
        return bundleQuantity;
    }

    public void setBundleQuantity(List<Long> bundleQuantity) {
        this.bundleQuantity = bundleQuantity;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }
}
