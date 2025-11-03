package com.example.part3.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        Long id,
        @NotBlank String name
) {}

