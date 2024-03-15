package com.example.TaskProject.repo;

import com.example.TaskProject.entity.SupplierClient;

import java.util.List;

public interface SupplierClientRepo {
    List<SupplierClient> getAll();
    SupplierClient getById();

    void add(SupplierClient supplierClient);
}
