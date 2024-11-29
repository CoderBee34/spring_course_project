package com.springcourse.project.service;

import com.springcourse.project.model.Equipment;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.repository.EquipmentRepository;
import com.springcourse.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public boolean addEquipmentToReservation(String reservationNumber, int equipmentCode){
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentCode);
        if (equipmentOptional.isPresent()){
            Equipment equipment = equipmentOptional.get();
            Optional<Reservation> reservationOptional = reservationRepository.findById(reservationNumber);
            if (reservationOptional.isPresent()){

                Reservation reservation = reservationOptional.get();
                List<Equipment> reservationEquipmentList = reservation.getEquipmentList();

                for (Equipment reservationEquipment : reservationEquipmentList){
                    if (reservationEquipment.getId() == equipment.getId()){
                        return false;
                    }
                }
                reservationEquipmentList.add(equipment);
                reservationRepository.save(reservation);
                return true;
            }
            return false;
        }
        return false;
    }
}
