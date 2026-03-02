package com.bbllc.sakilaspring.service;

import com.bbllc.sakilaspring.model.Film;
import com.bbllc.sakilaspring.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {

        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {

        return filmRepository.findAll();
    }

    public List<Map<String, Object>> getTopFilms() {
        List<Object[]> results = filmRepository.findTopRentedMovies();
        List<Map<String, Object>> topFilms = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> film = new HashMap<>();
            film.put("title", row[0]);
            film.put("description", row[1]);
            film.put("releaseYear", row[2]);
            film.put("rating", row[3]);
            film.put("specialFeatures", row[4]);
            film.put("rentalCount", row[5]);
            topFilms.add(film);
        }

        return topFilms;
    }

    public List<Map<String, Object>> getTopMoviesForActor(Integer actorId) {
        List<Object[]> results = filmRepository.findTopMoviesForActor(actorId);
        List<Map<String, Object>> topMovies = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> movie = new HashMap<>();
            movie.put("film_id", row[0]);
            movie.put("title", row[1]);
            movie.put("rental_count", row[2]);
            topMovies.add(movie);
        }

        return topMovies;
    }

    private List<Map<String, Object>> mapFilmRows(List<Object[]> results) {
        List<Map<String, Object>> films = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> film = new HashMap<>();
            film.put("film_id", row[0]);
            film.put("title", row[1]);
            film.put("description", row[2]);
            film.put("release_year", row[3]);
            film.put("rating", row[4]);
            film.put("special_features", row[5]);
            films.add(film);
        }
        return films;
    }

    public List<Map<String, Object>> getFilmsByGenre(String genreName) {
        return mapFilmRows(filmRepository.findFilmsByGenre("%" + genreName + "%"));
    }

    public List<Map<String, Object>> getFilmsByActor(String actorName) {
        return mapFilmRows(filmRepository.findFilmsByActor("%" + actorName + "%"));
    }

    public List<Map<String, Object>> getFilmsByTitle(String title) {
        return mapFilmRows(filmRepository.findFilmsByTitle("%" + title + "%"));
    }

    private Map<String, Object> mapMovieInfoRow(Object[] row) {
        Map<String, Object> film = new HashMap<>();
        film.put("film_id", row[0]);
        film.put("film_title", row[1]);
        film.put("number_of_copies", row[2]);
        film.put("number_of_rentals_out", row[3]);
        film.put("remaining_copies", row[4]);
        return film;
    }

    public List<Map<String, Object>> getAllMovieInfo() {
        return filmRepository.findAllMovieInfo()
                .stream()
                .map(this::mapMovieInfoRow)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getMovieInfoById(Integer filmId) {
        Object[] result = filmRepository.findMovieInfoById(filmId);
        if (result == null) return null;
        return mapMovieInfoRow(result);
    }

}