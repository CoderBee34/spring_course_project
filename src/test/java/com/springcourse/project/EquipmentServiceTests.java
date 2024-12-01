package com.springcourse.project;

import com.springcourse.project.model.Equipment;
import com.springcourse.project.model.Member;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.repository.EquipmentRepository;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
import com.springcourse.project.service.EquipmentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class EquipmentServiceTests {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void addEquipmentToReservationTest(){
        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        memberRepository.save(sampleMember);

        // Create a sample equipment
        Equipment sampleEquipment = new Equipment();
        sampleEquipment.setId(1);
        sampleEquipment.setName("Sample Equipment");
        sampleEquipment.setPrice(50.0);
        equipmentRepository.save(sampleEquipment);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setMember(sampleMember); // Set the member
        reservationRepository.save(sampleReservation);

        // Add equipment to reservation
        boolean result = equipmentService.addEquipmentToReservation("78912341", 1);
        // Add the same one again
        boolean result2 = equipmentService.addEquipmentToReservation("78912341", 1);
        // Add a non-existing equipment
        boolean result3 = equipmentService.addEquipmentToReservation("78912341", 999);

        // Verify the equipment was added successfully
        assertTrue(result);
        assertFalse(result2);
        assertFalse(result3);

        // Retrieve the updated reservation
        Optional<Reservation> updatedReservationOptional = reservationRepository.findById("78912341");
        assertTrue(updatedReservationOptional.isPresent());
        Reservation updatedReservation = updatedReservationOptional.get();

        // Verify the equipment is in the reservation's equipment list
        assertFalse(updatedReservation.getEquipmentList().isEmpty());
        assertEquals(updatedReservation.getEquipmentList().get(0).getId(), 1);
        assertEquals(updatedReservation.getEquipmentList().get(0).getName(), "Sample Equipment");
        assertEquals(updatedReservation.getEquipmentList().get(0).getPrice(), 50.0);
    }
}
