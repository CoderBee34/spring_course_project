package com.springcourse.project;

import com.springcourse.project.model.Member;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.model.ServiceModel;
import com.springcourse.project.repository.CarRepository;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
import com.springcourse.project.repository.ServiceRepository;
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
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void addServiceToReservation(){
        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        memberRepository.save(sampleMember);

        // Create a sample service
        ServiceModel sampleService = new ServiceModel();
        sampleService.setId(1);
        sampleService.setName("Sample Service");
        sampleService.setPrice(100.0);
        serviceRepository.save(sampleService);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setMember(sampleMember); // Set the member
        reservationRepository.save(sampleReservation);

        // Add service to reservation
        boolean result = serviceService.addServiceToReservation("78912341", 1);
        //Add the same one again
        boolean result2 = serviceService.addServiceToReservation("78912341", 1);
        //Add a non-existing service
        boolean result3 = serviceService.addServiceToReservation("78912341", 999);

        // Verify the service was added successfully
        assertTrue(result);
        assertFalse(result2);
        assertFalse(result3);

        // Retrieve the updated reservation
        Optional<Reservation> updatedReservationOptional = reservationRepository.findById("78912341");
        assertTrue(updatedReservationOptional.isPresent());
        Reservation updatedReservation = updatedReservationOptional.get();

        // Verify the service is in the reservation's service list
        assertFalse(updatedReservation.getServiceList().isEmpty());
        assertEquals(updatedReservation.getServiceList().get(0).getId(), 1);
        assertEquals(updatedReservation.getServiceList().get(0).getName(), "Sample Service");
        assertEquals(updatedReservation.getServiceList().get(0).getPrice(), 100.0);
    }

}
