package com.example.part3.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class ResourceDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Location is required")
    private String location;

    // Default constructor
    public ResourceDTO() {}

    // Parameterized constructor
    public ResourceDTO(Long id, String name, String category, String location) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
