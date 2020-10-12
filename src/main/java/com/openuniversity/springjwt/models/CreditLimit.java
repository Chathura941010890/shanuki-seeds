package com.openuniversity.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "credit_limitx")
public class CreditLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotBlank
    @Column(name = "limitx")
    private String limitx;


    public CreditLimit(@NotBlank String limitx) {
        this.limitx = limitx;
        setLimit(limitx);
    }

    public CreditLimit(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLimit() {
        return limitx;
    }

    public void setLimit(String limitx) {
        this.limitx = limitx;
    }
}
