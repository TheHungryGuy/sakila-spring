package com.bbllc.sakilaspring.controller;

import com.bbllc.sakilaspring.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Map<String, Object>>> getCustomerList() {
        return ResponseEntity.ok(customerService.getCustomerList());
    }

    @GetMapping("/check_customer/{customer_id}")
    public ResponseEntity<Map<String, Object>> checkCustomer(@PathVariable Integer customer_id) {
        return ResponseEntity.ok(customerService.checkCustomer(customer_id));
    }

    @PostMapping("/add_customer")
    public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody Map<String, String> data) {
        return ResponseEntity.ok(customerService.addCustomer(data));
    }

    @PutMapping("/update_customer/{customer_id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable Integer customer_id,
                                                              @RequestBody Map<String, String> data) {
        return ResponseEntity.ok(customerService.updateCustomer(customer_id, data));
    }

    @DeleteMapping("/delete_customer/{customer_id}")
    public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable Integer customer_id) {
        return ResponseEntity.ok(customerService.deleteCustomer(customer_id));
    }



}
