package com.example.Datasoft.Inventory.Management.Service;


import com.example.Datasoft.Inventory.Management.Dto.ProductDto;
import com.example.Datasoft.Inventory.Management.Entity.Products;
import com.example.Datasoft.Inventory.Management.Repository.ProductRepository;
import com.example.Datasoft.Inventory.Management.Dto.ProductUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Add a product
    public Products addProduct(ProductDto dto) {

        Products product = new Products(
                dto.getName(),
                dto.getCode(),
                dto.getBrandId(),
                dto.getCategoryId(),
                dto.getDescription(),
                dto.getStatus()
        );
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }


    //Get all products
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Products> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Update product
    public Products updateProduct(Long id, ProductUpdateDto dto) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (dto.getName() != null) existingProduct.setName(dto.getName());
                    if (dto.getCode() != null) existingProduct.setCode(dto.getCode());
                    if (dto.getBrandId() != null) existingProduct.setBrandId(dto.getBrandId());
                    if (dto.getCategoryId() != null) existingProduct.setCategoryId(dto.getCategoryId());
                    if (dto.getDescription() != null) existingProduct.setDescription(dto.getDescription());
                    if (dto.getStatus() != null) existingProduct.setStatus(dto.getStatus());

                    existingProduct.setUpdatedAt(java.time.LocalDateTime.now());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    //Delete Product
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
