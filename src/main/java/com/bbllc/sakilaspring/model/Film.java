package com.bbllc.sakilaspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("film_id")
    private Short filmId;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    @JsonProperty("release_year")
    private Integer releaseYear;

    @Column(length = 10)
    private String rating;

    @Column(length = 255)
    @JsonProperty("special_features")
    private String specialFeatures;

    @JsonIgnore
    @OneToMany(mappedBy = "film")
    private List<FilmActor> actors;


}

