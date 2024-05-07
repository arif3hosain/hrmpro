package com.hrm.hrmpro.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class PerformanceReviewDTO {

    private Long id;

    @Size(max = 255)
    private String reviewText;

    private LocalDate reviewDate;

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

}
