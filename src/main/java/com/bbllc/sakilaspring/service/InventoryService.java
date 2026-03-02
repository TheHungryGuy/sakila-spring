package com.bbllc.sakilaspring.service;

import com.bbllc.sakilaspring.repository.InventoryRepository;
import com.bbllc.sakilaspring.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Map<String, Object>> getRemainingInventory(Integer filmId) {
        List<Object[]> results = inventoryRepository.findRemainingInventory(filmId);
        List<Map<String, Object>> inventory = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> item = new HashMap<>();
            item.put("inventory_id", row[0]);
            item.put("film_id", row[1]);
            item.put("film_title", row[2]);
            inventory.add(item);
        }

        return inventory;
    }

}
