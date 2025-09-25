package com.example.Datasoft.Inventory.Management.Controller;

import com.example.Datasoft.Inventory.Management.Entity.Products;
import com.example.Datasoft.Inventory.Management.Service.ProductService;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add a product
    @PostMapping("/add")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        Products savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Get all products
    @GetMapping("")
    public ResponseEntity<List<Products>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        return ResponseEntity.of(productService.getProductById(id));
    }

    //Update Product by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    //Delete product by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}

