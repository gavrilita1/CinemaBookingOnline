package com.example.cinemaBookingOnline.controller;

import com.example.cinemaBookingOnline.model.dto.SeatResponseDto;
import com.example.cinemaBookingOnline.model.entities.User;
import com.example.cinemaBookingOnline.repository.UserRepository;
import com.example.cinemaBookingOnline.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("/me")
    public User me(Principal principal) {
        return userRepository.findByEmail(principal.getName()).orElseThrow();
    }
}
