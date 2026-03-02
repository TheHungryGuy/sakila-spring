package com.bbllc.sakilaspring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short customerId;

    @Column
    private Byte store_id;

    @Column(length = 45)
    private String firstName;

    @Column(length = 45)
    private String lastName;

    @Column(length = 50)
    private String email;

    @Column
    private Short address_id;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime lastUpdate;
}


