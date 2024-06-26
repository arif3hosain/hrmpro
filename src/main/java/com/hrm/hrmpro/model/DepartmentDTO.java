package com.hrm.hrmpro.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class DepartmentDTO {

    private Long id;

    @NotNull
    @Size(max = 100)
    @DepartmentNameUnique
    private String name;

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

}
