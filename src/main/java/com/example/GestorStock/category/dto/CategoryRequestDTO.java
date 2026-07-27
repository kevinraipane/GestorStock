package com.example.GestorStock.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @NotBlank(message = "El nombre de la categoria es obligatorio")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
        String name,

        @NotBlank(message = "La descripcion es obligatoria")
        @Size(max = 500, message = "La descripcion no puede exceder los 500 caracteres")
        String description
) {}
