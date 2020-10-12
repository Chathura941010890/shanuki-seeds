package com.openuniversity.springjwt.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "basic_order_details")
@EntityListeners(AuditingEntityListener.class)
public class BasicOrderDetails extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerDetails customerDetails;

    @NotBlank
    @Temporal(TIMESTAMP)
    @Column(name = "order_deadline")
    private Date deadline;

    @NotBlank
    @Column(name = "confirmedStsus")
    private boolean confirmedStatus;

    @NotBlank
    @Column(name = "order_placed_status")
    private boolean orderPlacedStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_done_by")
    private EmployeeDetails employeeDetails;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_placed_type")
    private OrderPlacedTypes orderPlacedTypes;


    @Temporal(TIMESTAMP)
    @Column(name = "order_placed_date")
    private Date orderPlaced;


    @Column(name = "order_placed_vehicle")
    private String vehicleNo;

    @NotBlank
    @Column(name = "is_invoiced")
    private boolean invoiced;



    public BasicOrderDetails(){

    }

    public BasicOrderDetails(@NotBlank CustomerDetails customerDetails, @NotBlank Date deadline, @NotBlank boolean confirmedStatus, @NotBlank boolean orderPlacedStatus, EmployeeDetails employeeDetails, OrderPlacedTypes orderPlacedTypes, Date orderPlaced, String vehicleNo, boolean invoiced) {
        this.customerDetails = customerDetails;
        this.deadline = deadline;
        this.confirmedStatus = confirmedStatus;
        this.orderPlacedStatus = orderPlacedStatus;
        this.employeeDetails = employeeDetails;
        this.orderPlacedTypes = orderPlacedTypes;
        this.orderPlaced = orderPlaced;
        this.vehicleNo = vehicleNo;
        this.invoiced = invoiced;
        setConfirmedStatus(confirmedStatus);
        setCustomerDetails(customerDetails);
        setDeadline(deadline);
        setEmployeeDetails(employeeDetails);
        setOrderPlaced(orderPlaced);
        setOrderPlacedStatus(orderPlacedStatus);
        setOrderPlacedTypes(orderPlacedTypes);
        setVehicleNo(vehicleNo);
        setInvoiced(invoiced);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isConfirmedStatus() {
        return confirmedStatus;
    }

    public void setConfirmedStatus(boolean confirmedStatus) {
        this.confirmedStatus = confirmedStatus;
    }

    public boolean isOrderPlacedStatus() {
        return orderPlacedStatus;
    }

    public void setOrderPlacedStatus(boolean orderPlacedStatus) {
        this.orderPlacedStatus = orderPlacedStatus;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public Date getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(Date orderPlaced) {
        this.orderPlaced = orderPlaced;
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
