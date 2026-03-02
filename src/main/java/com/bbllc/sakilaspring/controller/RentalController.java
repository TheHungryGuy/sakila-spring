package com.bbllc.sakilaspring.controller;

import com.bbllc.sakilaspring.service.CustomerService;
import com.bbllc.sakilaspring.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/customer_rentals/{customer_id}")
    public ResponseEntity<Map<String, Object>> getCustomerRentals(@PathVariable Integer customer_id) {
        Map<String, Object> response = new HashMap<>();
        response.put("rentals", rentalService.getCustomerRentals(customer_id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/rent_movie/{inventory_id}/{customer_id}")
    public ResponseEntity<Map<String, Object>> rentMovie(@PathVariable Integer inventory_id,
                                                         @PathVariable Integer customer_id) {
        return ResponseEntity.ok(rentalService.rentMovie(inventory_id, customer_id));
    }

    @PutMapping("/update_return_date/{rental_id}")
    public ResponseEntity<Map<String, Object>> updateReturnDate(@PathVariable Integer rental_id) {
        Map<String, Object> response = rentalService.updateReturnDate(rental_id);

        if (response.containsKey("error")) {
            String error = (String) response.get("error");
            if (error.equals("Rental not found")) return ResponseEntity.status(404).body(response);
            if (error.equals("Rental already Returned")) return ResponseEntity.status(400).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
