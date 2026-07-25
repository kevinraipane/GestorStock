package com.example.GestorStock.category.service.impl;

import com.example.GestorStock.category.dto.CategoryRequestDTO;
import com.example.GestorStock.category.dto.CategoryResponseDTO;
import com.example.GestorStock.category.entity.Category;
import com.example.GestorStock.category.mapper.CategoryMapper;
import com.example.GestorStock.category.repository.CategoryRepository;
import com.example.GestorStock.category.service.CategoryService;
import com.example.GestorStock.common.globalHandler.customException.BusinessRuleException;
import com.example.GestorStock.common.globalHandler.customException.DuplicateResourceException;
import com.example.GestorStock.common.globalHandler.customException.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO request){
        if (categoryRepository.existsByName(request.name())){
            throw new BusinessRuleException("Ya existe una categoria con el nombre: " +request.name());
        }

        Category category = categoryMapper.toEntity(request);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " +id));

        if (!category.getName().equalsIgnoreCase(request.name()) && categoryRepository.existsByName(request.name())) {
            throw new DuplicateResourceException("La categoria con el nombre '" +request.name()+ "' ya existe");
        }

        category.setName(request.name());
        category.setDescription(request.description());

        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deactivateCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " +id));

        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " +id));
        return  categoryMapper.toDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryResponseDTO> getAllActiveCategories(Pageable pageable) {
        return categoryRepository.findByActiveTrue(pageable)
                .map(categoryMapper::toDto);
    }
}
