package com.example.TaskProject.service;

import com.example.TaskProject.repo.ProductRepoImpl;
import com.example.TaskProject.repo.SupplierClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private ProductRepoImpl productRepoImpl;
    private SupplierClientRepo supplierClientRepo;

    @Autowired
    public ApplicationService(ProductRepoImpl productRepoImpl, SupplierClientRepo supplierClientRepo) {
        this.productRepoImpl = productRepoImpl;
        this.supplierClientRepo = supplierClientRepo;
    }

}
