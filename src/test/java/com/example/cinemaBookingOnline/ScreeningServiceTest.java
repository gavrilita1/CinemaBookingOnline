package com.example.cinemaBookingOnline;

import com.example.cinemaBookingOnline.model.dto.ScreeningRequestDTO;
import com.example.cinemaBookingOnline.model.dto.ScreeningResponseDto;
import com.example.cinemaBookingOnline.model.entities.CinemaRoom;
import com.example.cinemaBookingOnline.model.entities.Movie;
import com.example.cinemaBookingOnline.model.entities.Screening;
import com.example.cinemaBookingOnline.repository.CinemaRoomRepository;
import com.example.cinemaBookingOnline.repository.MovieRepository;
import com.example.cinemaBookingOnline.repository.ScreeningRepository;
import com.example.cinemaBookingOnline.service.impl.ScreeningServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScreeningServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ScreeningRepository screeningRepository;
    @Mock
    private CinemaRoomRepository cinemaRoomRepository;

    @InjectMocks
    private ScreeningServiceImpl screeningService;


    @Test
    void createScreening_success(){
        //given
        ScreeningRequestDTO requestDTO = new ScreeningRequestDTO(1L,1L,20.0);

        Movie movie = new Movie();
        movie.setId(1L);

        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setId(1L);

        Screening screening = new Screening();
        screening.setId(2L);
        screening.setMovie(movie);
        screening.setCinemaRoom(cinemaRoom);
        screening.setPrice(20.0);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(cinemaRoomRepository.findById(1L)).thenReturn(Optional.of(cinemaRoom));
        when(screeningRepository.save(any(Screening.class))).thenReturn(screening);

        //when
        ScreeningResponseDto responseDto = screeningService.createScreening(requestDTO);

        //then

        assertNotNull(responseDto);
        assertEquals(2L, responseDto.id());
        assertEquals(20.0, responseDto.price());

        verify(movieRepository).findById(1L);
        verify(cinemaRoomRepository).findById(1L);
        verify(screeningRepository).save(any(Screening.class));


    }


}
