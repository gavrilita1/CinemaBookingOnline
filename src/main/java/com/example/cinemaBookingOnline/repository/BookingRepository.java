package com.example.cinemaBookingOnline.repository;

import com.example.cinemaBookingOnline.model.entities.Booking;
import com.example.cinemaBookingOnline.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {


//    @Query("select distinct b from Booking" +
//            "join b.seats where " +
//            "b.screeningId = :screeningId and s.id in :seatIds b.status in :statuses")
//    List<Booking> findConflictBookings(Long screeningId,
//                                       Set<Long> seatIds,
//                                       List<BookingStatus> statuses);
}
