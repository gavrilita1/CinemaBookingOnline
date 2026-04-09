package com.example.cinemaBookingOnline.controller;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.dto.ScreeningResponseDto;
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
    public ScreeningResponseDto createScreening(@RequestBody ScreeningRequestDTO dto) {
        return screeningService.createScreening(dto);
    }

    @GetMapping
    public List<ScreeningResponseDto> getAllScreenings() {
        return screeningService.getAllScreenings();
    }

    @GetMapping("/id/{id}")
    public ScreeningResponseDto getScreeningById(@PathVariable Long id) {
        return screeningService.getScreeningById(id);
    }

    @GetMapping("/movie/{movieId}")
    public List<ScreeningResponseDto> getScreeningsByMovieId(@PathVariable Long movieId) {
        return screeningService.getScreeningsByMovieId(movieId);
    }

    @GetMapping("/room/{roomId}")
    public List<ScreeningResponseDto> getScreeningsByRoomId(@PathVariable Long roomId) {
        return screeningService.getScreeningsByRoomId(roomId);
    }

    @GetMapping("/period")
    public List<ScreeningResponseDto> getScrenningsByPeriod(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return screeningService.getScrenningsByPeriod(start, end);
    }

    @GetMapping("/period/query")
    public List<ScreeningResponseDto> getScrenningsByPeriodQuery(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return screeningService.getScrenningsByPeriodQuery(start, end);
    }

    @PutMapping("/{id}")
    public ScreeningResponseDto updateScreening(@PathVariable Long id, @RequestBody ScreeningRequestDTO dto) {
        return screeningService.updateScreening(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
    }
}
