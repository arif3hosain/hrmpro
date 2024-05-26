package com.hrm.hrmpro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;


@Entity
public class Job {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, name = "\"description\"", length = 5000)
    private String description;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false)
    private Double salary;

    @Column(columnDefinition = "boolean default true")
    public boolean active;

    @Column
    private LocalDate postedDate;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
