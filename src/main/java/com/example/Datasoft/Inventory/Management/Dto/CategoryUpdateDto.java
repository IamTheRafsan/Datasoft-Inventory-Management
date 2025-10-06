package com.example.Datasoft.Inventory.Management.Dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CategoryUpdateDto {

    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Category code must be between 2 and 50 characters")
    private String code;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public LocalDateTime getCreatedAt() { return created_at; }
    public void setCreatedAt(LocalDateTime createdAt) { this.created_at = createdAt; }

    public LocalDateTime getUpdatedAt() { return updated_at; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updated_at = updatedAt; }


}
