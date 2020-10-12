package com.openuniversity.springjwt.payload.response;

import com.openuniversity.springjwt.models.CustomerDetails;
import com.openuniversity.springjwt.models.EmployeeDetails;
import com.openuniversity.springjwt.models.OrderPlacedTypes;

import java.util.Date;

public class BasicOrderDetailsResponse {
    CustomerDetails customerDetails;
    Date deadline;
    boolean confirmed;
    boolean placed;
    Date orderPlacedDate;
    EmployeeDetails orderPlacedEmployee;
    OrderPlacedTypes orderPlacedTypes;
    String vehicleNo;
    boolean invoiced;


    public BasicOrderDetailsResponse(CustomerDetails customerDetails, Date deadline, boolean confirmed, boolean placed, Date orderPlacedDate, EmployeeDetails orderPlacedEmployee, OrderPlacedTypes orderPlacedTypes, String vehicleNo, boolean invoiced) {
        this.customerDetails = customerDetails;
        this.deadline = deadline;
        this.confirmed = confirmed;
        this.placed = placed;
        this.orderPlacedDate = orderPlacedDate;
        this.orderPlacedEmployee = orderPlacedEmployee;
        this.orderPlacedTypes = orderPlacedTypes;
        this.vehicleNo = vehicleNo;
        this.invoiced = invoiced;
    }

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

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }
}
