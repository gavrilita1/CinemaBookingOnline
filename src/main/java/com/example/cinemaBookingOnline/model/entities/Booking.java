package com.example.cinemaBookingOnline.model.entities;

import com.example.cinemaBookingOnline.model.enums.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private BookingStatus status = BookingStatus.NONE;
}
