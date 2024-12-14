package com.springcourse.project.controller;

import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.dto.ReservationRequestDTO;
import com.springcourse.project.model.Equipment;
import com.springcourse.project.model.ServiceModel;
import com.springcourse.project.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @Operation(	summary = "Make a reservation with the given information's.",
            description = "Returns the reservation info of user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation successfully created.",
                    content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "206", description = "Selected car is not available, reservation didn't created.")
        }
    )
    @RequestMapping(value = "/make",method = RequestMethod.POST)
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
        return ResponseEntity.status(200).body(reservationDTO);
    }
    @Operation(	summary = "Cancel a reservation with the given information's.",
            description = "Returns the boolean result of operation."
    )
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public ResponseEntity<Boolean> cancelReservation(@RequestParam String reservationNumber){
        try {
            Boolean result = reservationService.cancelReservation(reservationNumber);
            if (result == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(404).body(Boolean.FALSE);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }
    }
    @Operation(	summary = "Delete the reservation with the given information.",
            description = "Returns the boolean result of operation."
    )
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteReservation(@RequestParam String reservationNumber){
        try {
            Boolean result = reservationService.deleteReservation(reservationNumber);
            if (result.booleanValue() == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(404).body(Boolean.FALSE);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }
    }

}
