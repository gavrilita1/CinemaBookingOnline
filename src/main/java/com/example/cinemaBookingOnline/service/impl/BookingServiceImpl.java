package com.example.cinemaBookingOnline.service.impl;

import com.example.cinemaBookingOnline.model.dto.BookingRequestDto;
import com.example.cinemaBookingOnline.model.dto.BookingResponseDto;
import com.example.cinemaBookingOnline.model.entities.Booking;
import com.example.cinemaBookingOnline.model.entities.Screening;
import com.example.cinemaBookingOnline.model.entities.Seat;
import com.example.cinemaBookingOnline.model.enums.BookingStatus;
import com.example.cinemaBookingOnline.repository.BookingRepository;
import com.example.cinemaBookingOnline.repository.ScreeningRepository;
import com.example.cinemaBookingOnline.repository.SeatRepository;
import com.example.cinemaBookingOnline.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ScreeningRepository screeningRepository;

    @Override
    public BookingResponseDto createBooking(BookingRequestDto dto) {

        Screening screening = screeningRepository.findById(dto.screeningId()).orElseThrow(() -> new RuntimeException("Screening not found"));

        Set<Long> seats = dto.seatIds();
        if (seats == null || seats.isEmpty()) throw new RuntimeException("Seats ids invalid");

        //Varianta distinct
        List<Booking> conflicts = bookingRepository.findConflictBookingsJPQL(dto.screeningId(), seats, List.of(BookingStatus.ONHOLD, BookingStatus.CONFIRMED));
        if (!conflicts.isEmpty()) {
            throw new RuntimeException("There are conflicts on your booking!");
        }

        //Varianta count
        Long conflictsCount = bookingRepository.conflictCountBooking(dto.screeningId(), seats, List.of(BookingStatus.ONHOLD, BookingStatus.CONFIRMED));
        if (conflictsCount > 0) {
            throw new RuntimeException("There are conflicts on your booking!");
        }

        Set<Seat> seatsSet = seatRepository.findAllById(seats)
                .stream()
                .collect(Collectors.toSet());

        Booking booking = new Booking();
        booking.setScreening(screening);
        booking.setSeats(seatsSet);
        booking.setCreatedAt(LocalDate.now());
        booking.setStatus(BookingStatus.ONHOLD);

        return toDto(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDto confirmBooking(Long id) {

        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        if (booking.getStatus() != BookingStatus.ONHOLD) {
            throw new RuntimeException("Booking status already changed or cannot be confirmed");
        }
        booking.setStatus(BookingStatus.CONFIRMED);

        return toDto(bookingRepository.save(booking));
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public BookingResponseDto getBookingById(Long id) {
        return toDto(bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found")));
    }
}
