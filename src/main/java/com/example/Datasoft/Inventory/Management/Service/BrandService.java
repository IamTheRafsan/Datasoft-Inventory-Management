package com.example.Datasoft.Inventory.Management.Service;


import com.example.Datasoft.Inventory.Management.Dto.BrandDto;
import com.example.Datasoft.Inventory.Management.Dto.BrandUpdateDto;
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
    public Brand addBrand(BrandDto dto) {

        Brand brand = new Brand();
        brand.setName(dto.getName());
        brand.setCode(dto.getCode());
        brand.setStatus(dto.getStatus());

        LocalDateTime now = LocalDateTime.now();
        brand.setCreatedAt(now);
        brand.setUpdatedAt(now);

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
    public Brand updateBrand(Long id, BrandUpdateDto dto){
        return brandRepository.findById(id)
                .map(existingBrand -> {
                    if (dto.getName() != null) {
                        existingBrand.setName(dto.getName());
                    }
                    if (dto.getCode() != null) {
                        existingBrand.setCode(dto.getCode());
                    }
                    if (dto.getStatus() != null) {
                        existingBrand.setStatus(dto.getStatus());
                    }

                    existingBrand.setUpdatedAt(LocalDateTime.now());

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
