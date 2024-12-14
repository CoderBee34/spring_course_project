package com.springcourse.project;

import com.springcourse.project.dto.AdditionalServiceRequestDTO;
import com.springcourse.project.model.Member;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.service.ServiceService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class ServiceServiceTests {
    @Autowired
    ServiceService serviceService;

    @Test
    @Transactional
    void addServiceToReservation(){
        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        serviceService.saveMemberForTest(sampleMember);

        // Create a sample service
        serviceService.createService(1,"Sample Service",100.0);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setMember(sampleMember);
        serviceService.saveReservationForTest(sampleReservation);

        // Add service to reservation
        boolean result = serviceService.addServiceToReservation(new AdditionalServiceRequestDTO("78912341", 1));
        //Add the same one again
        boolean result2 = serviceService.addServiceToReservation(new AdditionalServiceRequestDTO("78912341", 1));
        //Add a non-existing service
        boolean result3 = serviceService.addServiceToReservation(new AdditionalServiceRequestDTO("78912341", 999));

        // Verify the service was added successfully
        assertTrue(result);
        assertFalse(result2);
        assertFalse(result3);

        // Retrieve the updated reservation
        Optional<Reservation> updatedReservationOptional = serviceService.findReservationByIdForTest("78912341");
        assertTrue(updatedReservationOptional.isPresent());
        Reservation updatedReservation = updatedReservationOptional.get();

        // Verify the service is in the reservation's service list
        assertFalse(updatedReservation.getServiceList().isEmpty());
        assertEquals(updatedReservation.getServiceList().get(0).getId(), 1);
        assertEquals(updatedReservation.getServiceList().get(0).getName(), "Sample Service");
        assertEquals(updatedReservation.getServiceList().get(0).getPrice(), 100.0);
    }

}
