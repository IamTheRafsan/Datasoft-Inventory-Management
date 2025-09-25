package com.example.Datasoft.Inventory.Management.Service;


import com.example.Datasoft.Inventory.Management.Entity.Products;
import com.example.Datasoft.Inventory.Management.Repository.ProductRepository;
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
    public Products addProduct(Products product) {

        product.setUpdatedAt(LocalDateTime.now());
        if (product.getCreatedAt() == null) {
            product.setCreatedAt(LocalDateTime.now());
        }
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
    public Products updateProduct(Long id, Products updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (updatedProduct.getName() != null) {
                        existingProduct.setName(updatedProduct.getName());
                    }
                    if (updatedProduct.getCode() != null) {
                        existingProduct.setCode(updatedProduct.getCode());
                    }
                    if (updatedProduct.getBrandId() != null) {
                        existingProduct.setBrandId(updatedProduct.getBrandId());
                    }
                    if (updatedProduct.getCategoryId() != null) {
                        existingProduct.setCategoryId(updatedProduct.getCategoryId());
                    }
                    if (updatedProduct.getDescription() != null) {
                        existingProduct.setDescription(updatedProduct.getDescription());
                    }
                    if (updatedProduct.getStatus() != null) {
                        existingProduct.setStatus(updatedProduct.getStatus());
                    }

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
