package com.springcourse.project.controller;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    CarService carService;

    @Operation(	summary = "Gets the available cars by given information's.",
            description = "Returns the available cars as list."
    )
    @RequestMapping(value="/available", method = RequestMethod.GET)
    public ResponseEntity<List<AvailableCarDTO>> searchAvailableCars(@RequestParam String carType, @RequestParam String transmissionType) {

        List<AvailableCarDTO> availableCarDTOList=carService.searchAvailableCars(carType, transmissionType);
        if (availableCarDTOList.isEmpty()){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(202).body(availableCarDTOList);
    }
    @Operation(	summary = "Gets the rented cars.",
            description = "Returns the rented cars as list."
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
    @RequestMapping(value="/rented", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> returnCar(@RequestParam String reservationNumber){
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
    @RequestMapping(value = "/{carBarcode}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCar(@PathVariable String carBarcode){
        if (!carService.isCarPresent(carBarcode)){
            return ResponseEntity.status(404).body(Boolean.FALSE);
        }
        try {
            Boolean result = carService.deleteCar(carBarcode);
            if (result == true){
                return ResponseEntity.status(200).body(Boolean.TRUE);
            }
            return ResponseEntity.status(406).body(Boolean.TRUE);
        } catch (Exception e){
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }

    }

}
