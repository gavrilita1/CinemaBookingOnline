package com.example.cinemaBookingOnline.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer col_number;
    private Integer row_number;

}
