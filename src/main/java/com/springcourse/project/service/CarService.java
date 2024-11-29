package com.springcourse.project.service;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.repository.CarRepository;
import com.springcourse.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public List<AvailableCarDTO> searchAvailableCars(String carType, String transmissionType) {
        List<Car> queryResult = carRepository.findByStatusAndTypeAndTransmissionType(CarStatus.fromString("Available"), CarType.fromString(carType), transmissionType);
        List<AvailableCarDTO> returnValue = queryResult.stream().map(car -> new AvailableCarDTO(car)).toList();
        return returnValue;
    }
    public void updateCarStatus(String barcode, CarStatus newStatus) {
        carRepository.updateCarStatusByBarcode(newStatus, barcode);
    }

    public List<RentedCarDTO> getRentedCars(){
        List<RentedCarDTO> rentedCarDTOList = new ArrayList<>();

        List<Reservation> reservationList = reservationRepository.findReservationsByStatusEquals(ReservationStatus.ACTIVE);
        reservationList.addAll(reservationRepository.findReservationsByStatusEquals(ReservationStatus.CONFIRMED));
        reservationList.addAll(reservationRepository.findReservationsByStatusEquals(ReservationStatus.NONE));
        for(Reservation reservation : reservationList){
            Optional<Car> carOptional = carRepository.findById(reservation.getCar().getBarcode());
            rentedCarDTOList.add(new RentedCarDTO(carOptional.get(), reservation));
        }
        return rentedCarDTOList;
    }
}
