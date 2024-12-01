package com.springcourse.project.service;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.repository.CarRepository;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<AvailableCarDTO> searchAvailableCars(String carType, String transmissionType) {
        List<Car> queryResult = carRepository.findByStatusAndTypeAndTransmissionType(CarStatus.fromString("Available"), CarType.fromString(carType), transmissionType);
        List<AvailableCarDTO> returnValue = queryResult.stream().map(car -> new AvailableCarDTO(car)).toList();
        return returnValue;
    }

    public List<RentedCarDTO> getRentedCars(){
        List<RentedCarDTO> rentedCarDTOList = new ArrayList<>();

        List<Reservation> reservationList = reservationRepository.findReservationsByStatusEquals(ReservationStatus.ACTIVE);
        reservationList.addAll(reservationRepository.findReservationsByStatusEquals(ReservationStatus.CONFIRMED));
        reservationList.addAll(reservationRepository.findReservationsByStatusEquals(ReservationStatus.NONE));
        reservationList.addAll(reservationRepository.findReservationsByStatusEquals(ReservationStatus.PENDING));
        for(Reservation reservation : reservationList){
            Optional<Car> carOptional = carRepository.findById(reservation.getCar().getBarcode());
            rentedCarDTOList.add(new RentedCarDTO(carOptional.get(), reservation));
        }
        return rentedCarDTOList;
    }

    @Transactional
    public boolean returnCar(String reservationNumber){
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationNumber);
        if (reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            Car car = reservation.getCar();
            if (car != null){
                carRepository.updateCarStatusByBarcode(CarStatus.AVAILABLE, car.getBarcode());
                reservationRepository.updateReservationStatusById(ReservationStatus.COMPLETED, reservationNumber);
                reservationRepository.updateReservationReturnDateById(LocalDate.now(), reservationNumber);
                return true;
            }
            return false;
        }
        return false;
    }

    @Transactional
    public boolean deleteCar(String barcode){
        Optional<Car> carOptional = carRepository.findById(barcode);
        if (carOptional.isPresent()){
            Car car = carOptional.get();
            if (car.getStatus() == CarStatus.AVAILABLE){
                List<Reservation> reservationList = reservationRepository.findReservationsByCarBarcode(barcode);
                if (reservationList.isEmpty()){
                    carRepository.deleteCarByBarcode(barcode);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Transactional
    public Car createCar(String barcode, String brand, String model, CarStatus carStatus, CarType carType, double mileage, String transmissionType){
        Car car = new Car();
        car.setBarcode(barcode);
        car.setBrand(brand);
        car.setModel(model);
        car.setStatus(carStatus);
        car.setType(carType);
        car.setMileage(mileage);
        car.setTransmissionType(transmissionType);
        carRepository.save(car);
        return car;
    }

    public Optional<Car> findCarById(String barcode){
        return carRepository.findById(barcode);
    }

    public void saveReservationForTest(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public void saveMemberForTest(Member member){
        memberRepository.save(member);
    }
    public Optional<Reservation> findReservationByIdForTest(String id){
        return reservationRepository.findById(id);
    }
    public List<Reservation> findReservationsByCarBarcode(String barcode){
        return reservationRepository.findReservationsByCarBarcode(barcode);
    }

}
