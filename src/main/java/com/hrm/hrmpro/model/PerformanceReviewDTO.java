package com.hrm.hrmpro.model;

import com.hrm.hrmpro.domain.Employee;
import com.hrm.hrmpro.util.Performance;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class PerformanceReviewDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String reviewText;

    private LocalDate reviewDate;

    @NotNull
    private Employee employee;
    @NotNull
    private Performance performance;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(final String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(final LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
}
