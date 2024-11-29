package com.springcourse.project.service;

import com.springcourse.project.dto.LocationDTO;
import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<ReservationDTO> allReservations() {
        return reservationRepository.findAll().stream().map(reservation -> new ReservationDTO(reservation)).toList();
    }
}
