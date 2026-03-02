package com.bbllc.sakilaspring.repository;

import com.bbllc.sakilaspring.model.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {

}
