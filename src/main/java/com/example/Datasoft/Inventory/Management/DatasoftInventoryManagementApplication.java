package com.example.Datasoft.Inventory.Management;

import com.example.Datasoft.Inventory.Management.Entity.Brand;
import com.example.Datasoft.Inventory.Management.Entity.Categories;
import com.example.Datasoft.Inventory.Management.Enum.Status;
import com.example.Datasoft.Inventory.Management.Repository.BrandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatasoftInventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasoftInventoryManagementApplication.class, args);
	}

}
