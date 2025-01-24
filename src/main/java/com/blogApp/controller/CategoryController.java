package com.blogApp.controller;
import com.blogApp.Dto.CategoryDto;
import com.blogApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")

public class CategoryController {
    @Autowired
    private  CategoryService categoryService;



    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto createCategory =this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);


    }
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long catId){
        CategoryDto updateCategory =this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);


    }
    @DeleteMapping("/{catId}")
    public ResponseEntity<Void> deleteCategory( @PathVariable Long catId){
        categoryService.deleteCategory(catId);
        return ResponseEntity.noContent().build();
    }

    //get all user
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long catId){
        return ResponseEntity.ok(this.categoryService.getCategory(catId));
    }
    //get user by id
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return  ResponseEntity.ok(this.categoryService.getCategories());
    }


}
