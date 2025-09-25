package com.example.Datasoft.Inventory.Management.Repository;

import com.example.Datasoft.Inventory.Management.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
}
