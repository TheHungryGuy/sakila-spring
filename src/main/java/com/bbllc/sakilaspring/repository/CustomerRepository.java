package com.bbllc.sakilaspring.repository;

import com.bbllc.sakilaspring.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.email, " +
            "address.address, city.city, country.country, address.phone, customer.store_id, " +
            "customer.create_date AS registration_date, customer.last_update " +
            "FROM customer " +
            "JOIN address ON customer.address_id = address.address_id " +
            "JOIN city ON address.city_id = city.city_id " +
            "JOIN country ON city.country_id = country.country_id " +
            "ORDER BY customer.customer_id", nativeQuery = true)
    List<Object[]> findCustomerList();
}


