package com.bbllc.sakilaspring.service;

import com.bbllc.sakilaspring.model.Customer;
import com.bbllc.sakilaspring.repository.ActorRepository;
import com.bbllc.sakilaspring.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Map<String, Object>> getCustomerList() {
        List<Object[]> results = customerRepository.findCustomerList();
        List<Map<String, Object>> customers = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> customer = new HashMap<>();
            customer.put("customer_id", row[0]);
            customer.put("first_name", row[1]);
            customer.put("last_name", row[2]);
            customer.put("email", row[3]);
            customer.put("address", row[4]);
            customer.put("city", row[5]);
            customer.put("country", row[6]);
            customer.put("phone", row[7]);
            customer.put("store_id", row[8]);
            customer.put("registration_date", row[9]);
            customer.put("last_update", row[10]);
            customers.add(customer);
        }

        return customers;
    }

    public Map<String, Object> checkCustomer(Integer customerId) {
        Map<String, Object> response = new HashMap<>();
        response.put("customer_exists", customerRepository.findById(customerId).isPresent());
        return response;
    }

    public Map<String, Object> addCustomer(Map<String, String> data) {
        Customer customer = new Customer();
        customer.setFirstName(data.get("first_name"));
        customer.setLastName(data.get("last_name"));
        customer.setEmail(data.get("email"));
        customer.setStore_id((byte) 1);
        customer.setAddress_id((short) 1);
        customerRepository.save(customer);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer added successfully");
        return response;
    }

    public Map<String, Object> updateCustomer(Integer customerId, Map<String, String> data) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customer.setFirstName(data.get("first_name"));
        customer.setLastName(data.get("last_name"));
        customer.setEmail(data.get("email"));
        customerRepository.save(customer);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer updated successfully");
        return response;
    }

    public Map<String, Object> deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer deleted successfully");
        return response;
    }

}
