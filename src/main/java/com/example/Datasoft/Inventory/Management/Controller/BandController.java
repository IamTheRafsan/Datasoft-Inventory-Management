package com.example.Datasoft.Inventory.Management.Controller;


import com.example.Datasoft.Inventory.Management.Entity.Brand;
import com.example.Datasoft.Inventory.Management.Entity.Products;
import com.example.Datasoft.Inventory.Management.Service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory/brand")
public class BandController {

    private final BrandService brandService;

    public BandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //add brand
    @PostMapping("/add")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand){
        Brand savedBrand = brandService.addBrand(brand);
        return ResponseEntity.ok(brand);
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
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.updateBrand(id, brand));
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
