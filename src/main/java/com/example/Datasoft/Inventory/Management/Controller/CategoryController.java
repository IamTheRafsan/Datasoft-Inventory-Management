package com.example.Datasoft.Inventory.Management.Controller;


import com.example.Datasoft.Inventory.Management.Entity.Brand;
import com.example.Datasoft.Inventory.Management.Entity.Category;
import com.example.Datasoft.Inventory.Management.Service.BrandService;
import com.example.Datasoft.Inventory.Management.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //add category
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category savedCategory = categoryService.addCategory(category); // save through service
        return ResponseEntity.ok(savedCategory);
    }


    //get all category
    @GetMapping("")
    public ResponseEntity<List<Category>> getCategory(){
        return ResponseEntity.ok(categoryService.getCategory());
    }


    // get brand by id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)// if found → return 200 with Brand
                .orElse(ResponseEntity.notFound().build()); // if not found → return 404
    }

    //update category
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    //delete category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}
