package com.hrm.hrmpro.model;

import jakarta.validation.constraints.Size;


public class BenefitDTO {

    private Long id;

    @Size(max = 100)
    private String name;

    @Size(max = 200)
    private String description;

    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

}
