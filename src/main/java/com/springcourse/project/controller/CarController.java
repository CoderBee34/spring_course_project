package com.springcourse.project.controller;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.AvailableCarRequestDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.service.CarService;
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
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    CarService carService;

    @Operation(	summary = "Gets the available cars by given car type and transmission type information's.",
            description = "Returns the available cars as list."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Available Car's with given information's as DTO list successfully got.",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "An exception occurred in the server side.",
                    content = @Content(schema = @Schema(implementation = List.class)))
        }
    )
    @RequestMapping(value="/available", method = RequestMethod.GET)
    public ResponseEntity<List<AvailableCarDTO>> searchAvailableCars(@RequestBody AvailableCarRequestDTO availableCarRequestDTO) {
        List<AvailableCarDTO> availableCarDTOList=carService.searchAvailableCars(availableCarRequestDTO);
        if (availableCarDTOList.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(availableCarDTOList);
    }
    @Operation(	summary = "Gets the rented cars.",
            description = "Returns the rented cars as list."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rented Car's as DTO list successfully got.",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "An exception occurred in the server side.",
                    content = @Content(schema = @Schema(implementation = List.class)))
        }
    )
    @RequestMapping(value="/rented", method = RequestMethod.GET)
    public ResponseEntity<List<RentedCarDTO>> getRentedCars(){
        List<RentedCarDTO> rentedCarDTOList = carService.getRentedCars();
        if (rentedCarDTOList.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(rentedCarDTOList);
    }

    @Operation(	summary = "Returns the car by given information's.",
            description = "Returns the boolean result of operation."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car successfully returned.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Selected car is not found.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "500", description = "An exception occurred in the server side.",
                    content = @Content(schema = @Schema(implementation = Boolean.class)))
        }
    )
    @RequestMapping(value="/rented/{reservation-number}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> returnCar(@PathVariable("reservation-number") String reservationNumber){
        try {
            Boolean result = carService.returnCar(reservationNumber);
            if (result == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(404).body(Boolean.FALSE);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }
    }
    @Operation(	summary = "Delete the car by given information.",
            description = "Returns the boolean result of operation."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car record successfully deleted..",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Selected car is not found.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "406", description = "Selected car is not available or used in another reservation.",
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "500", description = "An exception occurred in the server side.",
                    content = @Content(schema = @Schema(implementation = Boolean.class)))
        }
    )
    @RequestMapping(value = "/{car-barcode}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCar(@PathVariable("car-barcode") String carBarcode){
        if (!carService.isCarPresent(carBarcode)){
            return ResponseEntity.status(404).body(Boolean.FALSE);
        }
        try {
            Boolean result = carService.deleteCar(carBarcode);
            if (result == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(406).body(Boolean.FALSE);
        } catch (Exception e){
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }

    }

}
