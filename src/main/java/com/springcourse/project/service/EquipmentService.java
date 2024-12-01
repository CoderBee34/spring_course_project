package com.springcourse.project.service;

import com.springcourse.project.model.Equipment;
import com.springcourse.project.model.Member;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.repository.EquipmentRepository;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
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

    @Transactional
    public Equipment createEquipment(int id, String name, double price){
        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setName(name);
        equipment.setPrice(price);
        equipmentRepository.save(equipment);
        return equipment;
    }
    public void saveReservationForTest(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public Optional<Reservation> findReservationByIdForTest(String id){
        return reservationRepository.findById(id);
    }
    public void saveMemberForTest(Member member){
        memberRepository.save(member);
    }

}
