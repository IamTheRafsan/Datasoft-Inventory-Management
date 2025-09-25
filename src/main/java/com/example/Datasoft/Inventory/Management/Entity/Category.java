package com.example.Datasoft.Inventory.Management.Entity;
import com.example.Datasoft.Inventory.Management.Enum.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    private LocalDateTime updated_at;


    //Default Constructor
    public Category() {}

    public Category(String name, String code, Status status) {
        this.name = name;
        this.code = code;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public LocalDateTime getCreatedAt() { return created_at; }
    public void setCreatedAt(LocalDateTime createdAt) { this.created_at = createdAt; }

    public LocalDateTime getUpdatedAt() { return updated_at; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updated_at = updatedAt; }
}
