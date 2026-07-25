package com.example.GestorStock.category.dto;

public record CategoryResponseDTO(
        Long idCategory,
        String name,
        String description,
        Boolean active
) {}
