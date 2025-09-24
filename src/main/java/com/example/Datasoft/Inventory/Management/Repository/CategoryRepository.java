package com.example.Datasoft.Inventory.Management.Repository;

import com.example.Datasoft.Inventory.Management.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Brand, Long>{

}
