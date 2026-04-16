package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.Booking;
import com.example.cinemaBookingOnline.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
                select distinct b 
                from Booking b 
                join b.seats s 
                where b.screening.id = :screeningId 
                  and s.id in :seatIds 
                  and b.status in :statuses
            """)
    List<Booking> findConflictBookingsJPQL(Long screeningId, Set<Long> seatIds, List<BookingStatus> statuses);


    @Query("""
            select count(b) 
            from Booking b
            join b.seats s
            where b.screening.id = :screeningId 
                and s.id in :seatIds
                and b.status in :statuses
            """)
    Long conflictCountBooking(Long screeningId, Set<Long> seatIds, List<BookingStatus> statuses);


}
