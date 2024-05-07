package com.hrm.hrmpro.model;


public class CompensationDTO {

    private Long id;
    private Double baseSalary;
    private Double bonus;
    private Double commission;
    private Double allowances;
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
