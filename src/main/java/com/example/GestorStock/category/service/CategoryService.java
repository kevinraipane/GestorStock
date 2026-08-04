package com.example.GestorStock.category.service;

import com.example.GestorStock.category.dto.CategoryRequestDTO;
import com.example.GestorStock.category.dto.CategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO request);
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);
    void deactivateCategory(Long id);
    CategoryResponseDTO getCategoryById(Long id);
    Page<CategoryResponseDTO> getAllActiveCategories(Pageable pageable);
}
