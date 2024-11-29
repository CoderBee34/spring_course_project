package com.springcourse.project.service;

import com.springcourse.project.dto.LocationDTO;
import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.repository.CarRepository;
import com.springcourse.project.repository.LocationRepository;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
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
    public ReservationDTO makeReservation(String carBarcode, int dayCount, long memberID, int pickUpLocationCode, int dropOffLocationCode, List<Equipment> equipmentList, List<com.springcourse.project.model.Service> serviceList){
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
                    LocalDate.now().plusDays(1+dayCount),
                    locationRepository.findById(pickUpLocationCode).get(),
                    locationRepository.findById(dropOffLocationCode).get(),
                    null,
                    ReservationStatus.PENDING,
                    memberRepository.findById(memberID).get()
            );
            newReservation.setEquipmentList(equipmentList);
            newReservation.setServiceList(serviceList);
            reservationRepository.save(newReservation);
            return new ReservationDTO(newReservation);
        }
        return null;
    }
}
