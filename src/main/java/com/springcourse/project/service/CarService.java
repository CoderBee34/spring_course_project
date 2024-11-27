package com.springcourse.project.service;

import com.springcourse.project.dto.CarDTO;
import com.springcourse.project.model.Car;
import com.springcourse.project.model.CarStatus;
import com.springcourse.project.model.CarType;
import com.springcourse.project.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> searchAvailableCars(String carType, String transmissionType) {
        List<Car> queryResult = carRepository.findByStatusAndTypeAndTransmissionType(CarStatus.fromString("Available"), CarType.fromString(carType), transmissionType);
        List<CarDTO> returnValue = queryResult.stream().map(car -> new CarDTO(
                car.getBrand(),
                car.getModel(),
                car.getType(),
                car.getMileage(),
                car.getTransmissionType(),
                car.getBarcode())).toList();
        return returnValue;
}
}
