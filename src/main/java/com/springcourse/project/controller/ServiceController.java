package com.springcourse.project.controller;

import com.springcourse.project.dto.AdditionalServiceRequestDTO;
import com.springcourse.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    public ResponseEntity<Boolean> addServiceToReservation(@RequestBody AdditionalServiceRequestDTO additionalServiceRequestDTO){
        String reservationNumber = additionalServiceRequestDTO.getReservationNumber();
        int serviceId = additionalServiceRequestDTO.getServiceId();
        try{
            Boolean result = serviceService.addServiceToReservation(reservationNumber,serviceId);
            if (result == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(404).body(Boolean.FALSE);
        } catch (Exception e){
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }
    }
}
