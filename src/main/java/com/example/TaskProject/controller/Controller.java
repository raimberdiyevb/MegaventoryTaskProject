package com.example.TaskProject.controller;

import com.example.TaskProject.entity.Product;
import com.example.TaskProject.repo.ProductRepo;
import com.example.TaskProject.repo.ProductRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private ProductRepoImpl productRepoImpl;

    @Autowired
    public Controller(ProductRepoImpl productRepoImpl) {
        this.productRepoImpl = productRepoImpl;
    }

    @GetMapping("api/project")
    public List<Product> test(){
        return productRepoImpl.getAllProducts();
    }
    @GetMapping("api/project/{productid}")
    public Product test(@PathVariable int productid){
        return productRepoImpl.getProductById(productid);
    }

    @PostMapping("api/project")
    public Product insert(@RequestBody Product product){
        productRepoImpl.add(product);
        return product;
    }

}
