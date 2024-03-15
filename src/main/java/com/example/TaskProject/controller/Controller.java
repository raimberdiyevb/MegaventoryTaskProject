package com.example.TaskProject.controller;

import com.example.TaskProject.entity.Product;
import com.example.TaskProject.entity.SupplierClient;
import com.example.TaskProject.repo.ProductRepo;
import com.example.TaskProject.repo.ProductRepoImpl;
import com.example.TaskProject.repo.SupplierClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private ProductRepoImpl productRepoImpl;
    private SupplierClientRepo supplierClientRepo;

    @Autowired
    public Controller(ProductRepoImpl productRepoImpl, SupplierClientRepo supplierClientRepo) {
        this.productRepoImpl = productRepoImpl;
        this.supplierClientRepo = supplierClientRepo;
    }

    @GetMapping("api/megaventory/products")
    public List<Product> test(){
        return productRepoImpl.getAllProducts();
    }
    @GetMapping("api/megaventory/products/{productid}")
    public Product test(@PathVariable int productid){
        return productRepoImpl.getProductById(productid);
    }

    @PostMapping("api/megaventory/products")
    public Product insert(@RequestBody Product product){
        productRepoImpl.add(product);
        return product;
    }

    @GetMapping("api/megaventory/clients")
    public List<SupplierClient> getAllClients(){
        return supplierClientRepo.getAll();
    }

}
