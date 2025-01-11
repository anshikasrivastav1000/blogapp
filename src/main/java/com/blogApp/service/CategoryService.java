package com.blogApp.service;

import com.blogApp.Dto.CategoryDto;
import com.blogApp.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);


    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

     void deleteCategory(Integer categoryId);

    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto>  getCategories();


}
