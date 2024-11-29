package com.springcourse.project.service;

import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.repository.CarRepository;
import com.springcourse.project.repository.LocationRepository;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<ReservationDTO> allReservations() {
        return reservationRepository.findAll().stream().map(reservation -> new ReservationDTO(reservation)).toList();
    }
    @Transactional
    public ReservationDTO makeReservation(String carBarcode, int dayCount, long memberID, int pickUpLocationCode, int dropOffLocationCode, List<Equipment> equipmentList, List<ServiceModel> serviceList){
        Optional<Car> reservedCarOptional = carRepository.findById(carBarcode);
        if (reservedCarOptional.isPresent()){
            Car reservedCar = reservedCarOptional.get();
            if(reservedCar.getStatus() != CarStatus.AVAILABLE){
                return null;
            }
            reservedCar.setStatus(CarStatus.LOANED);
            carRepository.updateCarStatusByBarcode(reservedCar.getStatus(), reservedCar.getBarcode());
            Reservation newReservation = new Reservation(
                    reservedCar,
                    LocalDate.now(),
                    LocalDate.now().plusDays(1),
                    LocalDate.now().plusDays(1 + dayCount),
                    locationRepository.findById(pickUpLocationCode).get(),
                    locationRepository.findById(dropOffLocationCode).get(),
                    null,
                    ReservationStatus.PENDING,
                    memberRepository.findById(memberID).get()
            );
            if (equipmentList != null)
                newReservation.setEquipmentList(equipmentList);
            if (serviceList != null)
                newReservation.setServiceList(serviceList);
            reservationRepository.save(newReservation);
            return new ReservationDTO(newReservation);
        }
        return null;
    }

    @Transactional
    public boolean cancelReservation(String reservationNumber){
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationNumber);
        if (reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            Car reservedCar = reservation.getCar();
            reservationRepository.updateReservationStatusById(ReservationStatus.CANCELLED, reservationNumber);
            carRepository.updateCarStatusByBarcode(CarStatus.AVAILABLE, reservedCar.getBarcode());
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteReservation(String reservationNumber){
        Car reservedCar = reservationRepository.findCarByReservationNumber(reservationNumber);
        if (reservedCar != null) {
            carRepository.updateCarStatusByBarcode(CarStatus.AVAILABLE, reservedCar.getBarcode());
            reservationRepository.deleteReservationById(reservationNumber);
            return true;
        }
        return false;
    }
}
