package com.springcourse.project.controller;

import com.springcourse.project.dto.AdditionalEquipmentRequestDTO;
import com.springcourse.project.dto.AdditionalServiceRequestDTO;
import com.springcourse.project.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addEquipmentToReservation(@RequestBody AdditionalEquipmentRequestDTO additionalEquipmentRequestDTO){
        String reservationNumber = additionalEquipmentRequestDTO.getReservationNumber();
        int serviceId = additionalEquipmentRequestDTO.getEquipmentId();
        try{
            Boolean result = equipmentService.addEquipmentToReservation(reservationNumber,serviceId);
            if (result == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(404).body(Boolean.FALSE);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }
    }
}
