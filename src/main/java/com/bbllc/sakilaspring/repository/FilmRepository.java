package com.bbllc.sakilaspring.repository;

import com.bbllc.sakilaspring.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {


    @Query(value = "SELECT f.title, f.description, f.release_year, f.rating, f.special_features," +
            " COUNT(r.rental_id) AS rental_count " +
            "FROM film f " +
            "JOIN inventory i ON f.film_id = i.film_id " +
            "JOIN rental r ON i.inventory_id = r.inventory_id " +
            "GROUP BY f.film_id " +
            "ORDER BY rental_count " +
            "DESC LIMIT 5", nativeQuery = true)
    List<Object[]> findTopRentedMovies();

    @Query(value = "SELECT f.film_id, f.title, COUNT(r.rental_id) AS rental_count " +
            "FROM film f " +
            "JOIN film_actor fa ON f.film_id = fa.film_id " +
            "JOIN actor a ON fa.actor_id = a.actor_id " +
            "JOIN inventory i ON f.film_id = i.film_id " +
            "JOIN rental r ON i.inventory_id = r.inventory_id " +
            "WHERE a.actor_id = :actorId " +
            "GROUP BY f.film_id, f.title " +
            "ORDER BY rental_count DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTopMoviesForActor(@Param("actorId") Integer actorId);

    @Query(value = "SELECT film.* FROM film " +
            "JOIN film_category ON film.film_id = film_category.film_id " +
            "JOIN category ON film_category.category_id = category.category_id " +
            "WHERE category.name LIKE :genreName", nativeQuery = true)
    List<Object[]> findFilmsByGenre(@Param("genreName") String genreName);

    @Query(value = "SELECT film.* FROM film " +
            "JOIN film_actor ON film.film_id = film_actor.film_id " +
            "JOIN actor ON film_actor.actor_id = actor.actor_id " +
            "WHERE CONCAT(actor.first_name, ' ', actor.last_name) LIKE :actorName", nativeQuery = true)
    List<Object[]> findFilmsByActor(@Param("actorName") String actorName);

    @Query(value = "SELECT * FROM film WHERE title LIKE :title", nativeQuery = true)
    List<Object[]> findFilmsByTitle(@Param("title") String title);


    @Query(value = "SELECT f.film_id, f.title AS film_title, " +
            "COALESCE(COUNT(DISTINCT i.inventory_id), 0) AS number_of_copies, " +
            "COALESCE(COUNT(DISTINCT r.rental_id), 0) AS number_of_rentals_out, " +
            "COALESCE(COUNT(DISTINCT i.inventory_id) - COUNT(DISTINCT r.rental_id), 0) AS remaining_copies " +
            "FROM film f " +
            "LEFT JOIN inventory i ON f.film_id = i.film_id " +
            "LEFT JOIN rental r ON i.inventory_id = r.inventory_id AND r.return_date IS NULL " +
            "GROUP BY f.film_id, f.title " +
            "ORDER BY f.film_id", nativeQuery = true)
    List<Object[]> findAllMovieInfo();

    @Query(value = "SELECT f.film_id, f.title AS film_title, " +
            "COALESCE(COUNT(DISTINCT i.inventory_id), 0) AS number_of_copies, " +
            "COALESCE(COUNT(DISTINCT r.rental_id), 0) AS number_of_rentals_out, " +
            "COALESCE(COUNT(DISTINCT i.inventory_id) - COUNT(DISTINCT r.rental_id), 0) AS remaining_copies " +
            "FROM film f " +
            "LEFT JOIN inventory i ON f.film_id = i.film_id " +
            "LEFT JOIN rental r ON i.inventory_id = r.inventory_id AND r.return_date IS NULL " +
            "WHERE f.film_id = :filmId " +
            "GROUP BY f.film_id, f.title", nativeQuery = true)
    Object[] findMovieInfoById(@Param("filmId") Integer filmId);
}


