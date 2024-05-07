package com.hrm.hrmpro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Compensation {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double baseSalary;

    @Column
    private Double bonus;

    @Column
    private Double commission;

    @Column
    private Double allowances;

    @Column
    private Double overtimePay;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(final Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(final Double bonus) {
        this.bonus = bonus;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(final Double commission) {
        this.commission = commission;
    }

    public Double getAllowances() {
        return allowances;
    }

    public void setAllowances(final Double allowances) {
        this.allowances = allowances;
    }

    public Double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(final Double overtimePay) {
        this.overtimePay = overtimePay;
    }

}
