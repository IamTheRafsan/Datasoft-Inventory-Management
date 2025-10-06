package com.example.Datasoft.Inventory.Management.Service;


import com.example.Datasoft.Inventory.Management.Dto.CategoryDto;
import com.example.Datasoft.Inventory.Management.Dto.CategoryUpdateDto;
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
    public Category addCategory(CategoryDto dto){

        Category category = new Category();
        category.setName(dto.getName());
        category.setCode(dto.getCode());

        LocalDateTime now = LocalDateTime.now();
        category.setCreatedAt(now);
        category.setUpdatedAt(now);

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
    public Category updateCategory(Long id, CategoryUpdateDto dto) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    if (dto.getName() != null) {
                        existingCategory.setName(dto.getName());
                    }
                    if (dto.getCode() != null) {
                        existingCategory.setCode(dto.getCode());
                    }

                    // Set the updatedAt timestamp on the entity
                    existingCategory.setUpdatedAt(LocalDateTime.now());

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
