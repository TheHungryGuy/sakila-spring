package com.bbllc.sakilaspring.repository;

import com.bbllc.sakilaspring.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query(value = "SELECT i.inventory_id, f.film_id, f.title AS film_title " +
            "FROM inventory i " +
            "JOIN film f ON i.film_id = f.film_id " +
            "LEFT JOIN rental r ON i.inventory_id = r.inventory_id AND r.return_date IS NULL " +
            "WHERE r.rental_id IS NULL AND f.film_id = :filmId " +
            "ORDER BY i.inventory_id", nativeQuery = true)
    List<Object[]> findRemainingInventory(@Param("filmId") Integer filmId);
}
