package com.example.part3.dto;

import jakarta.validation.constraints.NotBlank;

public record LocationDTO(
        Long id,
        @NotBlank String building,
        @NotBlank String room
) {}
