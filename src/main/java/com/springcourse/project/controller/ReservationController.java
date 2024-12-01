package com.springcourse.project.controller;

import com.springcourse.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
}
