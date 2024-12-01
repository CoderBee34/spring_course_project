package com.springcourse.project;

import com.springcourse.project.dto.ReservationDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class ReservationServiceTests {
    @Autowired
    ReservationService reservationService;

    @Test
    void makeReservationTest(){
        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        reservationService.saveMemberForTest(sampleMember);

        // Create a sample car
        Car sampleCar = new Car();
        sampleCar.setBarcode("12345");
        sampleCar.setStatus(CarStatus.AVAILABLE);
        sampleCar.setDailyPrice(100.0);
        reservationService.saveCarForTest(sampleCar);

        // Create sample locations
        Location pickUpLocation = new Location();
        pickUpLocation.setCode(1);
        pickUpLocation.setName("Location A");
        reservationService.saveLocationForTest(pickUpLocation);

        Location dropOffLocation = new Location();
        dropOffLocation.setCode(2);
        dropOffLocation.setName("Location B");
        reservationService.saveLocationForTest(dropOffLocation);

        // Create sample equipment
        Equipment equipment1 = new Equipment();
        equipment1.setId(1);
        equipment1.setName("GPS");
        equipment1.setPrice(10.0);
        reservationService.saveEquipmentForTest(equipment1);

        Equipment equipment2 = new Equipment();
        equipment2.setId(2);
        equipment2.setName("Baby Seat");
        equipment2.setPrice(15.0);
        reservationService.saveEquipmentForTest(equipment2);

        List<Equipment> equipmentList = Arrays.asList(equipment1, equipment2);

        // Create sample services
        ServiceModel service1 = new ServiceModel();
        service1.setId(1);
        service1.setName("Insurance");
        service1.setPrice(20.0);
        reservationService.saveServiceForTest(service1);

        ServiceModel service2 = new ServiceModel();
        service2.setId(2);
        service2.setName("Roadside Assistance");
        service2.setPrice(5.0);
        reservationService.saveServiceForTest(service2);

        List<ServiceModel> serviceList = Arrays.asList(service1, service2);

        // Make the reservation
        ReservationDTO reservationDTO = reservationService.makeReservation(
                "12345", 5, 1L, 1, 2, equipmentList, serviceList);

        // Verify the reservation was created successfully
        assertNotNull(reservationDTO);
        assertNotNull(reservationDTO.getReservationNumber());
        assertEquals(8, reservationDTO.getReservationNumber().length());
        assertEquals(LocalDate.now().plusDays(1), reservationDTO.getPickUpDateTime());
        assertEquals(LocalDate.now().plusDays(6), reservationDTO.getDropOffDateTime());
        assertEquals("Location A", reservationDTO.getPickUpLocation().getName());
        assertEquals("Location B", reservationDTO.getDropOffLocation().getName());


        // Verify the car status is updated to LOANED
        Optional<Car> carOptional = reservationService.findCarByIdForTest("12345");
        assertTrue(carOptional.isPresent());
        Car car = carOptional.get();
        assertEquals(CarStatus.LOANED, car.getStatus());

        // Verify the total amount calculation
        double expectedTotalAmount = 5 * 100.0 + 10.0 + 15.0 + 20.0 + 5.0;
        assertEquals(expectedTotalAmount, reservationDTO.getTotalAmount());

    }

    @Test
    void cancelReservationTest(){
        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        reservationService.saveMemberForTest(sampleMember);

        // Create a sample car
        Car sampleCar = new Car();
        sampleCar.setBarcode("12345");
        sampleCar.setStatus(CarStatus.RESERVED);
        reservationService.saveCarForTest(sampleCar);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setStatus(ReservationStatus.ACTIVE);
        sampleReservation.setCar(sampleCar);
        sampleReservation.setMember(sampleMember);
        reservationService.saveReservation(sampleReservation);

        // Cancel the reservation
        boolean result = reservationService.cancelReservation("78912341");

        // Verify the reservation was cancelled successfully
        assertTrue(result);

        // Retrieve the updated reservation
        Optional<Reservation> updatedReservationOptional = reservationService.findReservationById("78912341");
        assertTrue(updatedReservationOptional.isPresent());
        Reservation updatedReservation = updatedReservationOptional.get();

        // Verify the reservation status is updated to CANCELLED
        assertEquals(ReservationStatus.CANCELLED, updatedReservation.getStatus());

        // Verify the car status is updated to Available
        assertEquals(CarStatus.AVAILABLE, updatedReservation.getCar().getStatus());

        // IF WANT TO TEST THE NON-EXISTENT RESERVATION CANCELLATION

        // Try to cancel a non-existent reservation
        //boolean result2 = reservationService.cancelReservation("99999999");

        // Verify the cancellation was not successful
        //assertFalse(result2);
    }

    @Test
    void deleteReservation(){
        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        reservationService.saveMemberForTest(sampleMember);

        // Create a sample car
        Car sampleCar = new Car();
        sampleCar.setBarcode("12345");
        sampleCar.setStatus(CarStatus.RESERVED);
        reservationService.saveCarForTest(sampleCar);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setStatus(ReservationStatus.ACTIVE);
        sampleReservation.setCar(sampleCar);
        sampleReservation.setMember(sampleMember);
        reservationService.saveReservation(sampleReservation);

        // Delete the reservation
        boolean result = reservationService.deleteReservation("78912341");

        // Verify the reservation was deleted successfully
        assertTrue(result);

        // Verify the reservation no longer exists
        Optional<Reservation> deletedReservationOptional = reservationService.findReservationById("78912341");
        assertFalse(deletedReservationOptional.isPresent());

        // Verify the car still exists and its status is updated to AVAILABLE
        Optional<Car> carOptional = reservationService.findCarByIdForTest(sampleCar.getBarcode());
        assertTrue(carOptional.isPresent());
        Car car = carOptional.get();
        assertEquals(CarStatus.AVAILABLE, car.getStatus());

        // Verify the member still exists
        Optional<Member> memberOptional = reservationService.findMemberByIdForTest(sampleMember.getId());
        assertTrue(memberOptional.isPresent());
    }
}
