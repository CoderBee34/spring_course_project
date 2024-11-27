package com.springcourse.project.controller;

import com.springcourse.project.dto.CarDTO;
import com.springcourse.project.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/all")
    public List<CarDTO> availableCars(){
        return carService.searchAvailableCars("Standard", "Automatic");
    }

}
