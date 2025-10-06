package com.example.Datasoft.Inventory.Management.Controller;


import com.example.Datasoft.Inventory.Management.Dto.BrandDto;
import com.example.Datasoft.Inventory.Management.Dto.BrandUpdateDto;
import com.example.Datasoft.Inventory.Management.Entity.Brand;
import com.example.Datasoft.Inventory.Management.Service.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/inventory/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //add brand
    @PostMapping("/add")
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody BrandDto dto) {
        Brand savedBrand = brandService.addBrand(dto);
        return ResponseEntity.ok(savedBrand);
    }

    //get all brands
    @GetMapping("")
    public ResponseEntity<List<Brand>> getBrand(){
        return ResponseEntity.ok(brandService.getBrand());
    }

    // get brand by id
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)           // if found → return 200 with Brand
                .orElse(ResponseEntity.notFound().build()); // if not found → return 404
    }

    //update brand
    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandUpdateDto dto) {
        return ResponseEntity.ok(brandService.updateBrand(id, dto));
    }

    //delete brand
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        boolean deleted = brandService.deleteBrand(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }




}
