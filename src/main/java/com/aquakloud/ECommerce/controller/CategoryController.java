package com.aquakloud.ECommerce.controller;

import com.aquakloud.ECommerce.model.Category;
import com.aquakloud.ECommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "success";
    }

    @GetMapping("/api")
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }
}
