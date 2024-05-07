package com.hrm.hrmpro.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public class PayrollDTO {

    private Long id;

    @NotNull
    private LocalDate payPeriodStartDate;

    @NotNull
    private LocalDate payPeriodEndDate;

    @NotNull
    private Double grossSalary;

    private Double deductions;

    @NotNull
    private Double netSalary;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public void setPayPeriodStartDate(final LocalDate payPeriodStartDate) {
        this.payPeriodStartDate = payPeriodStartDate;
    }

    public LocalDate getPayPeriodEndDate() {
        return payPeriodEndDate;
    }

    public void setPayPeriodEndDate(final LocalDate payPeriodEndDate) {
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(final Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(final Double deductions) {
        this.deductions = deductions;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(final Double netSalary) {
        this.netSalary = netSalary;
    }

}
