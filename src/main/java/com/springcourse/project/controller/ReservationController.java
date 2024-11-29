package com.springcourse.project.controller;

import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @GetMapping("/all")
    public List<ReservationDTO> allReservations(){
        return reservationService.allReservations();
    }
}
