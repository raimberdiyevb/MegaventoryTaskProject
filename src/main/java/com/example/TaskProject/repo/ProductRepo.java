package com.example.TaskProject.repo;

import com.example.TaskProject.entity.Product;

import java.util.List;

public interface ProductRepo {
    void add(Product product);
    //void updateProductByID(int id);

    Product getProductById(int id);

    List<Product> getAllProducts();

    //void deleteById(int id);
}
