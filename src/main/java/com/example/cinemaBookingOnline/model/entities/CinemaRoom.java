package com.example.cinemaBookingOnline.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cinema_rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cols_count;
    private Integer rows_count;
    private String name;
}
