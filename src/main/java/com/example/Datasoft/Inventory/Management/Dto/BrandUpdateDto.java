package com.example.Datasoft.Inventory.Management.Dto;

import com.example.Datasoft.Inventory.Management.Enum.Status;
import jakarta.validation.constraints.Size;

public class BrandUpdateDto {

    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Brand code must be between 2 and 50 characters")
    private String code;

    private Status status;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
