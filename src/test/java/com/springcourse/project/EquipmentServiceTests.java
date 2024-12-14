package com.springcourse.project;

import com.springcourse.project.dto.AdditionalEquipmentRequestDTO;
import com.springcourse.project.model.Equipment;
import com.springcourse.project.model.Member;
import com.springcourse.project.model.Reservation;
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

    @Test
    @Transactional
    void addEquipmentToReservationTest(){
        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        equipmentService.saveMemberForTest(sampleMember);

        // Create a sample equipment
        Equipment sampleEquipment = equipmentService.createEquipment(1,"Sample Equipment",50.0);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setMember(sampleMember); // Set the member
        equipmentService.saveReservationForTest(sampleReservation);

        // Add equipment to reservation
        boolean result = equipmentService.addEquipmentToReservation(new AdditionalEquipmentRequestDTO("78912341", 1));
        // Add the same one again
        boolean result2 = equipmentService.addEquipmentToReservation(new AdditionalEquipmentRequestDTO("78912341", 1));
        // Add a non-existing equipment
        boolean result3 = equipmentService.addEquipmentToReservation(new AdditionalEquipmentRequestDTO("78912341", 999));

        // Verify the equipment was added successfully
        assertTrue(result);
        assertFalse(result2);
        assertFalse(result3);

        // Retrieve the updated reservation
        Optional<Reservation> updatedReservationOptional = equipmentService.findReservationByIdForTest("78912341");
        assertTrue(updatedReservationOptional.isPresent());
        Reservation updatedReservation = updatedReservationOptional.get();

        // Verify the equipment is in the reservation's equipment list
        assertFalse(updatedReservation.getEquipmentList().isEmpty());
        assertEquals(updatedReservation.getEquipmentList().get(0).getId(), 1);
        assertEquals(updatedReservation.getEquipmentList().get(0).getName(), "Sample Equipment");
        assertEquals(updatedReservation.getEquipmentList().get(0).getPrice(), 50.0);
    }
}
