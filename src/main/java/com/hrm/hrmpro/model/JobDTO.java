package com.hrm.hrmpro.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class JobDTO {

    private Long id;

    @NotNull
    @Size(max = 150)
    private String title;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    @Size(max = 100)
    private String department;

    @NotNull
    private Double salary;

    private LocalDate postedDate;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(final Double salary) {
        this.salary = salary;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(final LocalDate postedDate) {
        this.postedDate = postedDate;
    }

}
