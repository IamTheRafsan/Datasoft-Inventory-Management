package com.example.Datasoft.Inventory.Management.Service;


import com.example.Datasoft.Inventory.Management.Entity.Brand;
import com.example.Datasoft.Inventory.Management.Entity.Category;
import com.example.Datasoft.Inventory.Management.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Create category
    public Category addCategory(Category category){

        category.setUpdatedAt(LocalDateTime.now());
        if(category.getCreatedAt() == null){
            category.setCreatedAt(LocalDateTime.now());
        }
        return categoryRepository.save(category);

    }

    //get category
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }

    //Get category by id
    public Optional<Category> getCategoryById(Long id){

        return categoryRepository.findById(id);

    }

    //update category
    public Category updateCategory(Long id, Category updatedCategory){
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    if (updatedCategory.getName() != null) {
                        existingCategory.setName(updatedCategory.getName());
                    }
                    if (updatedCategory.getCode() != null) {
                        existingCategory.setCode(updatedCategory.getCode());
                    }

                    updatedCategory.setUpdatedAt(java.time.LocalDateTime.now());

                    return categoryRepository.save(existingCategory);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

    }

    //delete Category
    public boolean deleteCategory(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
