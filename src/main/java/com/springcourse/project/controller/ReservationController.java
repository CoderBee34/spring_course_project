package com.springcourse.project.controller;

import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.dto.ReservationRequestDTO;
import com.springcourse.project.model.Equipment;
import com.springcourse.project.model.ReservationStatus;
import com.springcourse.project.model.ServiceModel;
import com.springcourse.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ReservationDTO> makeReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
        String carBarcode = reservationRequestDTO.getCarBarcode();
        int dayCount = reservationRequestDTO.getDayCount();
        long memberID = reservationRequestDTO.getMemberID();
        int pickUpLocationCode = reservationRequestDTO.getPickUpLocationCode();
        int dropOffLocationCode = reservationRequestDTO.getDropOffLocationCode();
        List<Equipment> equipmentList = reservationRequestDTO.getEquipmentList();
        List< ServiceModel> serviceList = reservationRequestDTO.getServiceList();
        ReservationDTO reservationDTO = reservationService.makeReservation( carBarcode,
                                                                            dayCount,
                                                                            memberID,
                                                                            pickUpLocationCode,
                                                                            dropOffLocationCode,
                                                                            equipmentList,
                                                                            serviceList);
        if (reservationDTO == null)
            return ResponseEntity.status(206).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(reservationDTO);
    }

}
