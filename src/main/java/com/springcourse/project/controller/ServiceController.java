package com.springcourse.project.controller;

import com.springcourse.project.dto.AdditionalServiceRequestDTO;
import com.springcourse.project.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    @Operation(	summary = "Add the service to reservation with the given service id and reservation number information.",
            description = "Returns the boolean result of operation."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service successfully added to reservation.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Selected service is not found.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "500", description = "An exception occurred in the server side.",
                    content = @Content(schema = @Schema(implementation = Boolean.class)))
        }
    )
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addServiceToReservation(@RequestBody AdditionalServiceRequestDTO additionalServiceRequestDTO){
        String reservationNumber = additionalServiceRequestDTO.getReservationNumber();
        int serviceId = additionalServiceRequestDTO.getServiceId();
        try{
            Boolean result = serviceService.addServiceToReservation(additionalServiceRequestDTO);
            if (result == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(404).body(Boolean.FALSE);
        } catch (Exception e){
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }
    }
}
