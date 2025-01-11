package com.blogApp.service;

import com.blogApp.Dto.CategoryDto;
import com.blogApp.entity.Category;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
    Category cat =   this.modelMapper.map(categoryDto, Category.class);
       Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                       .orElseThrow(() -> new ResourceNotFoundException("Category with this id Not Found"));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
     Category updatedCat =   this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() ->new ResourceNotFoundException("category not found with this id:" +categoryId));
        this.categoryRepo.delete(cat);

    }


    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found with this id :"+categoryId));

        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
   List<CategoryDto> catDtos =     categories.stream().map((cat) -> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
    return catDtos;

    }
}
