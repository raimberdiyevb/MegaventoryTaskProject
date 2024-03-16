package com.example.TaskProject.service;

import com.example.TaskProject.entity.InventoryLocation;
import com.example.TaskProject.entity.Product;
import com.example.TaskProject.entity.SupplierClient;
import com.example.TaskProject.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ProductRepoImpl productRepoImpl;
    private final SupplierClientRepoImpl supplierClientRepoImpl;
    private final InventoryLocationRepoImpl inventoryLocationRepoImpl;

    @Autowired
    public ApplicationService(ProductRepoImpl productRepoImpl, SupplierClientRepoImpl supplierClientRepoImpl, InventoryLocationRepoImpl inventoryLocationRepoImpl) {
        this.productRepoImpl = productRepoImpl;
        this.supplierClientRepoImpl = supplierClientRepoImpl;
        this.inventoryLocationRepoImpl = inventoryLocationRepoImpl;
    }
    public List<Product> getAllProducts(){
        return productRepoImpl.getAll();
    }
    public Product getProductById(int id){
        return productRepoImpl.getProductById(id);
    }
    public void insertProduct(Product product){
        productRepoImpl.add(product);
    }

    public List<SupplierClient> getAllSupplierClients() {
        return supplierClientRepoImpl.getAll();
    }

    public void insertSupplierClient(SupplierClient supplierClient,String supplierOrClient){
        supplierClientRepoImpl.add(supplierClient,supplierOrClient);
    }

    public List<InventoryLocation> getAllInventoryLocations(){
        return inventoryLocationRepoImpl.getAll();
    }

    public void insertInventoryLocation(InventoryLocation inventoryLocation){
        inventoryLocationRepoImpl.add(inventoryLocation);
    }
}
