package com.educative.ecommerce.controller;


import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

  /* @GetMapping("/categoryId")
    public ResponseEntity<Category> getCategoriesDetails(@PathVariable("categoryID") Integer categoryID) {
        Category CategoryInfo = categoryService.getCategoriesInfo(categoryID);
        return new ResponseEntity<>(CategoryInfo, HttpStatus.OK);
    }
    */
  @GetMapping(path = "/categoryId",produces = "application/json")
  public Category getCategoriesDetails(@RequestParam Integer categoryID)
  {
     //Category CategoryInfo = categoryService.getCategoriesInfo(categoryID);
      return categoryService.getCategoriesInfo(categoryID);

  }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }


    @PutMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @Valid @RequestBody Category category) {
        // Check to see if the category exists.
        if (Objects.nonNull(categoryService.readCategory(categoryID))) {
            // If the category exists then update it.
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }
        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}