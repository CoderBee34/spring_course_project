package com.springcourse.project;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class CarServiceTests {
    @Autowired
    CarService carService;

    @Test
    void searchAvailableCarTest(){
        // Create a sample car
        carService.createCar("12345",
                "Toyota",
                "Corolla",
                CarStatus.AVAILABLE,
                CarType.STANDARD,
                15000,
                "Automatic");

        // Search for available cars
        List<AvailableCarDTO> carDTOList = carService.searchAvailableCars("Standard", "Automatic");

        // Verify the list is not null and contains the expected car
        assertNotNull(carDTOList);
        assertFalse(carDTOList.isEmpty());
        AvailableCarDTO availableCarDTO = carDTOList.get(0);
        assertEquals(availableCarDTO.getBarcode(), "12345");
        assertEquals(availableCarDTO.getBrand(), "Toyota");
        assertEquals(availableCarDTO.getModel(), "Corolla");
        assertEquals(availableCarDTO.getCarType(), CarType.STANDARD);
        assertEquals(availableCarDTO.getMileage(), 15000);
        assertEquals(availableCarDTO.getTransmissionType(), "Automatic");
    }
    @Test
    void getRentedCarsTest(){
        // Create a sample car
        Car sampleCar = carService.createCar("12345",
                "Toyota",
                "Corolla",
                CarStatus.LOANED,
                CarType.STANDARD,
                15000,
                "Automatic");

        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setName("John Doe");
        // Set other required member properties
        carService.saveMemberForTest(sampleMember);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setCar(sampleCar);
        sampleReservation.setMember(sampleMember);
        sampleReservation.setStatus(ReservationStatus.ACTIVE);
        carService.saveReservationForTest(sampleReservation);

        // Retrieve rented cars
        List<RentedCarDTO> rentedCarDTOList = carService.getRentedCars();

        // Verify the rented car is in the list
        assertFalse(rentedCarDTOList.isEmpty());
        RentedCarDTO rentedCarDTO = rentedCarDTOList.get(0);
        assertEquals(rentedCarDTO.getBarcode(), "12345");
    }

    @Test
    void returnCarTest(){
        // Create a sample car
        Car sampleCar = carService.createCar("12345",
                "Toyota",
                "Corolla",
                CarStatus.LOANED,
                CarType.STANDARD,
                15000,
                "Automatic");

        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        carService.saveMemberForTest(sampleMember);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setCar(sampleCar);
        sampleReservation.setMember(sampleMember);
        sampleReservation.setStatus(ReservationStatus.ACTIVE);
        carService.saveReservationForTest(sampleReservation);

        // Verify initial status
        assertEquals(CarStatus.LOANED, sampleCar.getStatus());
        assertEquals(ReservationStatus.ACTIVE, sampleReservation.getStatus());

        // Return the car
        boolean result = carService.returnCar("78912341");

        // Verify the car status is updated to AVAILABLE
        Optional<Car> carOptional = carService.findCarById("12345");
        assertTrue(carOptional.isPresent());
        Car car = carOptional.get();
        assertEquals(CarStatus.AVAILABLE, car.getStatus());

        // Verify the reservation status is updated to COMPLETED
        Optional<Reservation> reservationOptional = carService.findReservationByIdForTest("78912341");
        assertTrue(reservationOptional.isPresent());
        Reservation reservation = reservationOptional.get();
        assertEquals(ReservationStatus.COMPLETED, reservation.getStatus());

        // Verify the return date is set to today
        assertEquals(LocalDate.now(), reservation.getReturnDate());

        // Verify the result is true
        assertTrue(result);

        // Test case: Reservation number does not exist
        boolean resultNonExistentReservation = carService.returnCar("nonExistentReservation");
        assertFalse(resultNonExistentReservation);

        // Test case: Car is not found
        Reservation reservationWithoutCar = new Reservation();
        reservationWithoutCar.setReservationNumber("78912342");
        reservationWithoutCar.setMember(sampleMember);
        reservationWithoutCar.setStatus(ReservationStatus.ACTIVE);
        carService.saveReservationForTest(reservationWithoutCar);

        boolean resultCarNotFound = carService.returnCar("78912342");
        assertFalse(resultCarNotFound);
    }

    @Test
    void deleteCarTest() {
        // Create a sample car
        Car sampleCar = carService.createCar("123456",
                "Toyota",
                "Corolla",
                CarStatus.AVAILABLE,
                CarType.STANDARD,
                15000,
                "Automatic");

        // Ensure the car is available
        Optional<Car> carOptional = carService.findCarById("123456");
        assertTrue(carOptional.isPresent());
        Car car = carOptional.get();
        assertEquals(CarStatus.AVAILABLE, car.getStatus());

        // Ensure there are no reservations for the car
        List<Reservation> reservations = carService.findReservationsByCarBarcode("123456");
        assertTrue(reservations.isEmpty());

        // Delete the car
        boolean deleteResult = carService.deleteCar("123456");
        assertTrue(deleteResult);

        // Verify the car is deleted
        carOptional = carService.findCarById("123456");
        assertFalse(carOptional.isPresent());

        // Test case: Car is not available
        Car loanedCar = carService.createCar("67890",
                "Toyota",
                "Corolla",
                CarStatus.LOANED,
                CarType.STANDARD,
                15000,
                "Automatic");

        boolean deleteLoanedCarResult = carService.deleteCar("67890");
        assertFalse(deleteLoanedCarResult);

        // Test case: Car has reservations
        Car reservedCar = carService.createCar("11223",
                "Toyota",
                "Corolla",
                CarStatus.AVAILABLE,
                CarType.STANDARD,
                15000,
                "Automatic");



        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        carService.saveMemberForTest(sampleMember);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("78912341");
        reservation.setCar(reservedCar);
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.setMember(sampleMember);
        carService.saveReservationForTest(reservation);

        boolean deleteReservedCarResult = carService.deleteCar("11223");
        assertFalse(deleteReservedCarResult);
    }

}
