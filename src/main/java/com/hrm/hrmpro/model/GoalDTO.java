package com.hrm.hrmpro.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class GoalDTO {

    private Long id;

    @Size(max = 500)
    private String goalDescription;

    private Boolean completed;

    private LocalDate targetDate;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(final String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(final Boolean completed) {
        this.completed = completed;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(final LocalDate targetDate) {
        this.targetDate = targetDate;
    }

}
