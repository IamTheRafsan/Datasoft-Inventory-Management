package com.example.Datasoft.Inventory.Management.Dto;

import com.example.Datasoft.Inventory.Management.Enum.Status;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ProductUpdateDto {

    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Product code must be between 2 and 50 characters")
    private String code;

    private Long brandId;
    private Long categoryId;

    @Size(max = 500, message = "Description can’t be longer than 500 characters")
    private String description;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Long getBrandId() { return brandId; }
    public void setBrandId(Long brandId) { this.brandId = brandId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
