package com.example.TaskProject.controller;

import com.example.TaskProject.entity.InventoryLocation;
import com.example.TaskProject.entity.Product;
import com.example.TaskProject.entity.SupplierClient;
import com.example.TaskProject.entity.SupplierClientRequest;
import com.example.TaskProject.repo.ProductRepo;
import com.example.TaskProject.repo.ProductRepoImpl;
import com.example.TaskProject.repo.SupplierClientRepo;
import com.example.TaskProject.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/raim/megaventory")
public class Controller {
    private final ApplicationService applicationService;

    @Autowired
    public Controller(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return applicationService.getAllProducts();
    }
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId){
        return applicationService.getProductById(productId);
    }

    @PostMapping("/products")
    public Product insertProduct(@RequestBody Product product){
        applicationService.insertProduct(product);
        return product;
    }

    @GetMapping("/clients")
    public List<SupplierClient> getAllSupplierClients(){
        return applicationService.getAllSupplierClients();
    }

    @PostMapping("/clients")
    public SupplierClient insertSupplierClient(@RequestBody SupplierClientRequest supplierClientRequest){
        String type = supplierClientRequest.getType();
        SupplierClient supplierClient = new SupplierClient(supplierClientRequest.getName(), supplierClientRequest.getEmail(), supplierClientRequest.getShippingAddress(), supplierClientRequest.getPhone());
        applicationService.insertSupplierClient(supplierClient,type);
        return supplierClient;
    }
    @GetMapping("/locations")
    public List<InventoryLocation> getAllInventoryLocations(){
        return applicationService.getAllInventoryLocations();
    }

    @PostMapping("/locations")
    public InventoryLocation insertInventoryLocation(@RequestBody InventoryLocation inventoryLocation){
        applicationService.insertInventoryLocation(inventoryLocation);
        return inventoryLocation;
    }

}
