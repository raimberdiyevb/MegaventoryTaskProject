package com.example.TaskProject.service;

import com.example.TaskProject.entity.InventoryLocation;
import com.example.TaskProject.entity.Product;
import com.example.TaskProject.entity.SupplierClient;
import com.example.TaskProject.repo.InventoryLocationRepo;
import com.example.TaskProject.repo.ProductRepoImpl;
import com.example.TaskProject.repo.SupplierClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.List;

@Service
public class ApplicationService {
    private final ProductRepoImpl productRepoImpl;
    private final SupplierClientRepo supplierClientRepo;
    private final InventoryLocationRepo inventoryLocationRepo;

    @Autowired
    public ApplicationService(ProductRepoImpl productRepoImpl, SupplierClientRepo supplierClientRepo, InventoryLocationRepo inventoryLocationRepo) {
        this.productRepoImpl = productRepoImpl;
        this.supplierClientRepo = supplierClientRepo;
        this.inventoryLocationRepo = inventoryLocationRepo;
    }
    public List<Product> getAllProducts(){
        return productRepoImpl.getAllProducts();
    }
    public Product getProductById(int id){
        return productRepoImpl.getProductById(id);
    }
    public void insertProduct(Product product){
        productRepoImpl.add(product);
    }

    public List<SupplierClient> getAllSupplierClients() {
        return supplierClientRepo.getAll();
    }

    public void insertSupplierClient(SupplierClient supplierClient,String supplierOrClient){
        supplierClientRepo.add(supplierClient,supplierOrClient);
    }

    public List<InventoryLocation> getAllInventoryLocations(){
        return inventoryLocationRepo.getAll();
    }

    public void insertInventoryLocation(InventoryLocation inventoryLocation){
        inventoryLocationRepo.add(inventoryLocation);
    }
}
