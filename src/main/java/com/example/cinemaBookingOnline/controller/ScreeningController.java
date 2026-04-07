package com.example.cinemaBookingOnline.controller;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.entities.Screening;
import com.example.cinemaBookingOnline.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/screenings")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @PostMapping
    public Screening createScreening(@RequestBody ScreeningRequestDTO dto) {
        return screeningService.createScreening(dto);
    }

    @GetMapping
    public List<Screening> getAllScreenings() {
        return screeningService.getAllScreenings();
    }

    @GetMapping("/id/{id}")
    public Screening getScreeningById(@PathVariable Long id) {
        return screeningService.getScreeningById(id);
    }

    @GetMapping("/movie/{movieId}")
    public List<Screening> getScreeningsByMovieId(@PathVariable Long movieId) {
        return screeningService.getScreeningsByMovieId(movieId);
    }

    @GetMapping("/room/{roomId}")
    public List<Screening> getScreeningsByRoomId(@PathVariable Long roomId) {
        return screeningService.getScreeningsByRoomId(roomId);
    }

    @GetMapping("/period")
    public List<Screening> getScrenningsByPeriod(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return screeningService.getScrenningsByPeriod(start, end);
    }

    @GetMapping("/period/query")
    public List<Screening> getScrenningsByPeriodQuery(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return screeningService.getScrenningsByPeriodQuery(start, end);
    }

    @PutMapping("/{id}")
    public Screening updateScreening(@PathVariable Long id, @RequestBody ScreeningRequestDTO dto) {
        return screeningService.updateScreening(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
    }
}
