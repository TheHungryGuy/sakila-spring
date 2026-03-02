package com.bbllc.sakilaspring.service;

import com.bbllc.sakilaspring.model.Rental;
import com.bbllc.sakilaspring.repository.CustomerRepository;
import com.bbllc.sakilaspring.repository.InventoryRepository;
import com.bbllc.sakilaspring.repository.RentalRepository;
import com.bbllc.sakilaspring.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    public RentalService(RentalRepository rentalRepository,
                         InventoryRepository inventoryRepository,
                         CustomerRepository customerRepository,
                         StaffRepository staffRepository) {
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }
    public List<Map<String, Object>> getCustomerRentals(Integer customerId) {
        List<Object[]> results = rentalRepository.findCustomerRentals(customerId);
        List<Map<String, Object>> rentals = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> rental = new HashMap<>();
            rental.put("rental_id", row[0]);
            rental.put("title", row[1]);
            rental.put("inventory_id", row[2]);
            rental.put("rental_date", row[3]);
            rental.put("return_date", row[4]);
            rentals.add(rental);
        }

        return rentals;
    }

    public Map<String, Object> rentMovie(Integer inventoryId, Integer customerId) {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setInventory(inventoryRepository.findById(inventoryId).orElseThrow());
        rental.setCustomer(customerRepository.findById(customerId).orElseThrow());
        rental.setStaff(staffRepository.findById(1).orElseThrow());
        rentalRepository.save(rental);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Movie rented successfully to ID#" + customerId);
        return response;
    }

    public Map<String, Object> updateReturnDate(Integer rentalId) {
        Map<String, Object> response = new HashMap<>();

        // find the rental, return 404 if not found
        Rental rental = rentalRepository.findById(rentalId).orElse(null);
        if (rental == null) {
            response.put("error", "Rental not found");
            return response;
        }

        // check if already returned
        if (rental.getReturnDate() != null) {
            response.put("error", "Rental already Returned");
            return response;
        }

        // update the return date
        rental.setReturnDate(LocalDateTime.now());
        rentalRepository.save(rental);

        response.put("message", "Return date updated successfully");
        return response;
    }
}
