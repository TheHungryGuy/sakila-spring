package com.bbllc.sakilaspring.controller;

import com.bbllc.sakilaspring.model.Film;
import com.bbllc.sakilaspring.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/all_films")
    public ResponseEntity<Map<String, Object>> getAllFilms() {
        Map<String, Object> response = new HashMap<>();
        response.put("films", filmService.getAllFilms());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top_rented_movies")
    public ResponseEntity<Map<String, Object>> getTopFilms(){
        Map<String, Object> response = new HashMap<>();
        response.put("top_movies", filmService.getTopFilms());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top_movies_for_actor/{actor_id}")
    public ResponseEntity<Map<String, Object>> getTopMoviesForActor(@PathVariable Integer actor_id) {
        Map<String, Object> response = new HashMap<>();
        response.put("top_movies", filmService.getTopMoviesForActor(actor_id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/films_by_genre")
    public ResponseEntity<Map<String, Object>> getFilmsByGenre(@RequestParam String genre_name) {
        Map<String, Object> response = new HashMap<>();
        response.put("films", filmService.getFilmsByGenre(genre_name));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/films_by_actor")
    public ResponseEntity<Map<String, Object>> getFilmsByActor(@RequestParam String actor_name) {
        Map<String, Object> response = new HashMap<>();
        response.put("films", filmService.getFilmsByActor(actor_name));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/films_by_title")
    public ResponseEntity<Map<String, Object>> getFilmsByTitle(@RequestParam String title) {
        Map<String, Object> response = new HashMap<>();
        response.put("films", filmService.getFilmsByTitle(title));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movie_info")
    public ResponseEntity<?> getMovieInfo(@RequestParam(required = false) Integer movie_id) {
        if (movie_id == null) {
            return ResponseEntity.ok(filmService.getAllMovieInfo());
        }
        Map<String, Object> result = filmService.getMovieInfoById(movie_id);
        if (result == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Movie not found");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok(result);
    }
}