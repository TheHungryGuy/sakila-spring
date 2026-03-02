package com.bbllc.sakilaspring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;

    // LocalDateTime is the Java equivalent of Flask's DateTime
    @Column
    private LocalDateTime rentalDate;

    // @ManyToOne replaces db.ForeignKey + db.relationship in one annotation
    // @JoinColumn tells JPA which column in the DB holds the foreign key
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @Column
    private LocalDateTime returnDate;

    // Same pattern again — one rental is handled by one staff member
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}