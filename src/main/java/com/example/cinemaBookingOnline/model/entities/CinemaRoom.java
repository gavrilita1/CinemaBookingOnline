package com.example.cinemaBookingOnline.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cinema_rooms")
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cols_count;
    private Integer rows_count;
    private String name;
}
