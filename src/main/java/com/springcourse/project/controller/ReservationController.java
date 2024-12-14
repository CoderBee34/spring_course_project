package com.springcourse.project.controller;

import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.dto.ReservationRequestDTO;
import com.springcourse.project.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        ReservationDTO reservationDTO = reservationService.makeReservation(reservationRequestDTO);
        if (reservationDTO == null)
            return ResponseEntity.status(206).body(null);
        return ResponseEntity.status(200).body(reservationDTO);
    }
    @Operation(	summary = "Cancel a reservation with the given information's.",
            description = "Returns the boolean result of operation."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation successfully cancelled.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Selected reservation is not found.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "500", description = "An exception occurred in the server side.",
                    content = @Content(schema = @Schema(implementation = Boolean.class)))
        }
    )
    @RequestMapping(value = "/cancel/{reservation-number}",method = RequestMethod.POST)
    public ResponseEntity<Boolean> cancelReservation(@PathVariable("reservation-number") String reservationNumber){
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation successfully deleted.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Selected reservation is not found.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "500", description = "An exception occurred in the server side.",
                    content = @Content(schema = @Schema(implementation = Boolean.class)))
        }
    )
    @RequestMapping(value = "/{reservation-number}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteReservation(@PathVariable("reservation-number") String reservationNumber){
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
