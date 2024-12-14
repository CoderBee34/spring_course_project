package com.springcourse.project.service;

import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.dto.ReservationRequestDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.repository.*;
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
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    ServiceRepository serviceRepository;

    public List<ReservationDTO> allReservations() {
        return reservationRepository.findAll().stream().map(reservation -> new ReservationDTO(reservation)).toList();
    }
    @Transactional
    public ReservationDTO makeReservation(ReservationRequestDTO reservationRequestDTO){
        String carBarcode = reservationRequestDTO.getCarBarcode();
        int dayCount = reservationRequestDTO.getDayCount();
        long memberID = reservationRequestDTO.getMemberID();
        int pickUpLocationCode = reservationRequestDTO.getPickUpLocationCode();
        int dropOffLocationCode = reservationRequestDTO.getDropOffLocationCode();
        List<Equipment> equipmentList = reservationRequestDTO.getEquipmentList();
        List< ServiceModel> serviceList = reservationRequestDTO.getServiceList();
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
                    ReservationStatus.CONFIRMED,
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
    public Optional<Reservation> findReservationById(String id){
        return reservationRepository.findById(id);
    }

    @Transactional
    public void saveReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public void saveMemberForTest(Member member){
        memberRepository.save(member);
    }
    public void saveCarForTest(Car car){
        carRepository.save(car);
    }
    public void saveLocationForTest(Location location){
        locationRepository.save(location);
    }
    public void saveEquipmentForTest(Equipment equipment){
        equipmentRepository.save(equipment);
    }
    public void saveServiceForTest(ServiceModel serviceModel){
        serviceRepository.save(serviceModel);
    }
    public Optional<Car> findCarByIdForTest(String barcode){
        return carRepository.findById(barcode);
    }
    public Optional<Member> findMemberByIdForTest(Long id){
        return memberRepository.findById(id);
    }

}
