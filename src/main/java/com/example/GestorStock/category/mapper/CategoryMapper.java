package com.example.GestorStock.category.mapper;

import com.example.GestorStock.category.dto.CategoryRequestDTO;
import com.example.GestorStock.category.dto.CategoryResponseDTO;
import com.example.GestorStock.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toDto(Category entity);
}
