package com.bbllc.sakilaspring.repository;

import com.bbllc.sakilaspring.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query(value = "SELECT rental.rental_id, film.title, rental.inventory_id, rental.rental_date, rental.return_date " +
            "FROM rental " +
            "INNER JOIN inventory ON rental.inventory_id = inventory.inventory_id " +
            "INNER JOIN film ON inventory.film_id = film.film_id " +
            "WHERE rental.customer_id = :customerId " +
            "ORDER BY rental.return_date IS NULL DESC, rental.return_date DESC", nativeQuery = true)
    List<Object[]> findCustomerRentals(@Param("customerId") Integer customerId);
}
