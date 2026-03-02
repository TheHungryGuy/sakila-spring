package com.bbllc.sakilaspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short actorId;

    @Column(length = 45)
    private String firstName;
    
    @Column(length = 45)
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "actor")
    private List<FilmActor> films;

}
