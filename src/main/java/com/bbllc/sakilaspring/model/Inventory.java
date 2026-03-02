package com.bbllc.sakilaspring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "inventory")
public class Inventory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;


//    @Column
//    private Integer availableCopies;
//    I never used this one last time

}


