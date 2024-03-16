package com.example.TaskProject.repo;

import com.example.TaskProject.entity.InventoryLocation;
import java.util.List;

public interface InventoryLocationRepo {
    List<InventoryLocation> getAll();
    void add(InventoryLocation inventoryLocation);
}
