package com.example.cinemaBookingOnline;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;
import com.example.cinemaBookingOnline.model.entities.Movie;
import com.example.cinemaBookingOnline.model.entities.Screening;
import com.example.cinemaBookingOnline.repository.CinemaRoomRepository;
import com.example.cinemaBookingOnline.repository.MovieRepository;
import com.example.cinemaBookingOnline.repository.ScreeningRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ScreeningIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Movie movie;
    private CinemaRoom cinemaRoom;

    @BeforeEach
    void setUp(){
        movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        cinemaRoom = new CinemaRoom();
        cinemaRoom.setName("Room 1");
        cinemaRoomRepository.save(cinemaRoom);
    }

    @AfterEach
    void cleanUp() {
        screeningRepository.deleteAll();
        movieRepository.deleteAll();
        cinemaRoomRepository.deleteAll();
    }

    @Test
    void whenCeateValidScreening_thenReturnScreening() throws Exception {
        ScreeningRequestDTO dto = new ScreeningRequestDTO(
                movie.getId(),
                cinemaRoom.getId(),
                50.0
        );

        mockMvc.perform(post("/api/screenings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.movieName").value("Test Movie"))
                .andExpect(jsonPath("$.cinemaRoom").value("Room 1"));

        assertEquals(1, screeningRepository.count());
    }

    @Test
    void whenCreateInvalitScreening_thenReturnScreening() throws Exception {
        ScreeningRequestDTO dto = new ScreeningRequestDTO(
                999L,
                cinemaRoom.getId(),
                50.0
        );

        assertThrows(Exception.class, () ->
                mockMvc.perform(post("/api/screenings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Movie not found"))));

        assertTrue(screeningRepository.findAll().isEmpty());
    }


    @Test
    void whenGetAllScreenings_thenReturnList() throws Exception {
        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setCinemaRoom(cinemaRoom);
        screening.setPrice(50.0);
        screening.setStartTime(LocalDate.now());

        screeningRepository.save(screening);

        mockMvc.perform(get("/api/screenings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }



}
