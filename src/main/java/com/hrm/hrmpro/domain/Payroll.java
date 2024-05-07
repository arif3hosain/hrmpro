package com.hrm.hrmpro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Payroll {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate payPeriodStartDate;

    @Column(nullable = false)
    private LocalDate payPeriodEndDate;

    @Column(nullable = false)
    private Double grossSalary;

    @Column
    private Double deductions;

    @Column(nullable = false)
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
