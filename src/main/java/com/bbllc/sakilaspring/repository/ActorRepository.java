package com.bbllc.sakilaspring.repository;

import com.bbllc.sakilaspring.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query(value = "SELECT " +
            "    a.actor_id, " +
            "    a.first_name, " +
            "    a.last_name, " +
            "    CONCAT(a.first_name, ' ', a.last_name) AS full_name, " +
            "    COUNT(f.film_id) AS film_count " +
            "FROM actor a " +
            "JOIN film_actor f ON a.actor_id = f.actor_id " +
            "GROUP BY a.actor_id, a.first_name, a.last_name " +
            "ORDER BY film_count DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTopActors();

}

