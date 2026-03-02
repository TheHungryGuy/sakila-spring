package com.bbllc.sakilaspring.controller;

import com.bbllc.sakilaspring.service.ActorService;
import com.bbllc.sakilaspring.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/remaining_inventory/{film_id}")
    public ResponseEntity<List<Map<String, Object>>> getRemainingInventory(@PathVariable Integer film_id) {
        return ResponseEntity.ok(inventoryService.getRemainingInventory(film_id));
    }
}
