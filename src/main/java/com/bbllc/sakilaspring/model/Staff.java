package com.bbllc.sakilaspring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "staff")
public class Staff{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte staffId;

}

