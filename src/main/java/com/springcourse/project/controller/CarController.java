package com.springcourse.project.controller;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.AvailableCarRequestDTO;
import com.springcourse.project.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    CarService carService;

    @RequestMapping(value="/available", method = RequestMethod.POST)
    public ResponseEntity<List<AvailableCarDTO>> searchAvailableCars(@RequestBody AvailableCarRequestDTO availableCarRequestDTO) {
        String carType = availableCarRequestDTO.getCarType();
        String transmissionType = availableCarRequestDTO.getTransmissionType();

        List<AvailableCarDTO> availableCarDTOList=carService.searchAvailableCars(carType, transmissionType);
        if (availableCarDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(availableCarDTOList);
    }

}
