package com.example.Datasoft.Inventory.Management.Service;


import com.example.Datasoft.Inventory.Management.Entity.Brand;
import com.example.Datasoft.Inventory.Management.Entity.Products;
import com.example.Datasoft.Inventory.Management.Repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;


    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    //Add a brand
    public Brand addBrand(Brand brand) {

        brand.setUpdatedAt(LocalDateTime.now());
        if(brand.getCreatedAt() == null) {
            brand.setCreatedAt(LocalDateTime.now());
        }
        return brandRepository.save(brand);

    }

    //Get all brands
    public List<Brand> getBrand(){

        return brandRepository.findAll();

    }

    //Get Brand by id
    public Optional<Brand> getBrandById(Long id){

        return brandRepository.findById(id);

    }

    //update brand by id
    public Brand updateBrand(Long id, Brand updatedBrand){
        return brandRepository.findById(id)
                .map(existingBrand -> {
                    if (updatedBrand.getName() != null) {
                        existingBrand.setName(updatedBrand.getName());
                    }
                    if (updatedBrand.getCode() != null) {
                        existingBrand.setCode(updatedBrand.getCode());
                    }
                    if (updatedBrand.getStatus() != null) {
                        existingBrand.setStatus(updatedBrand.getStatus());
                    }

                    existingBrand.setUpdatedAt(java.time.LocalDateTime.now());

                    return brandRepository.save(existingBrand);
                })
                .orElseThrow(() -> new RuntimeException("Brand not found with id " + id));
    }

    //delete brand
    public boolean deleteBrand(Long id){
        if(brandRepository.existsById(id)){
            brandRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }


}
