package com.springcourse.project;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.model.*;
import com.springcourse.project.repository.CarRepository;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
import com.springcourse.project.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
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
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void searchAvailableCarTest(){
        // Create a sample car
        Car sampleCar = new Car();
        sampleCar.setBarcode("12345");
        sampleCar.setBrand("Toyota");
        sampleCar.setModel("Corolla");
        sampleCar.setStatus(CarStatus.AVAILABLE);
        sampleCar.setType(CarType.STANDARD);
        sampleCar.setMileage(15000);
        sampleCar.setTransmissionType("Automatic");
        carRepository.save(sampleCar);

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
        Car sampleCar = new Car();
        sampleCar.setBarcode("12345");
        sampleCar.setStatus(CarStatus.LOANED);
        carRepository.save(sampleCar);

        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setName("John Doe");
        // Set other required member properties
        memberRepository.save(sampleMember);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setCar(sampleCar);
        sampleReservation.setMember(sampleMember);
        sampleReservation.setStatus(ReservationStatus.ACTIVE);
        reservationRepository.save(sampleReservation);

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
        Car sampleCar = new Car();
        sampleCar.setBarcode("12345");
        sampleCar.setStatus(CarStatus.LOANED);
        carRepository.save(sampleCar);

        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        memberRepository.save(sampleMember);

        // Create a sample reservation
        Reservation sampleReservation = new Reservation();
        sampleReservation.setReservationNumber("78912341");
        sampleReservation.setCar(sampleCar);
        sampleReservation.setMember(sampleMember);
        sampleReservation.setStatus(ReservationStatus.ACTIVE);
        reservationRepository.save(sampleReservation);

        // Verify initial status
        assertEquals(CarStatus.LOANED, sampleCar.getStatus());
        assertEquals(ReservationStatus.ACTIVE, sampleReservation.getStatus());

        // Return the car
        boolean result = carService.returnCar("78912341");

        // Verify the car status is updated to AVAILABLE
        Optional<Car> carOptional = carRepository.findById("12345");
        assertTrue(carOptional.isPresent());
        Car car = carOptional.get();
        assertEquals(CarStatus.AVAILABLE, car.getStatus());

        // Verify the reservation status is updated to COMPLETED
        Optional<Reservation> reservationOptional = reservationRepository.findById("78912341");
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
        reservationRepository.save(reservationWithoutCar);

        boolean resultCarNotFound = carService.returnCar("78912342");
        assertFalse(resultCarNotFound);
    }

    @Test
    void deleteCarTest() {
        // Create a sample car
        Car sampleCar = new Car();
        sampleCar.setBarcode("123456");
        sampleCar.setStatus(CarStatus.AVAILABLE);
        carRepository.save(sampleCar);

        // Ensure the car is available
        Optional<Car> carOptional = carRepository.findById("123456");
        assertTrue(carOptional.isPresent());
        Car car = carOptional.get();
        assertEquals(CarStatus.AVAILABLE, car.getStatus());

        // Ensure there are no reservations for the car
        List<Reservation> reservations = reservationRepository.findReservationsByCarBarcode("123456");
        assertTrue(reservations.isEmpty());

        // Delete the car
        boolean deleteResult = carService.deleteCar("123456");
        assertTrue(deleteResult);

        // Verify the car is deleted
        carOptional = carRepository.findById("123456");
        assertFalse(carOptional.isPresent());

        // Test case: Car is not available
        Car loanedCar = new Car();
        loanedCar.setBarcode("67890");
        loanedCar.setStatus(CarStatus.LOANED);
        carRepository.save(loanedCar);

        boolean deleteLoanedCarResult = carService.deleteCar("67890");
        assertFalse(deleteLoanedCarResult);

        // Test case: Car has reservations
        Car reservedCar = new Car();
        reservedCar.setBarcode("11223");
        reservedCar.setStatus(CarStatus.AVAILABLE);
        carRepository.save(reservedCar);

        // Create a sample member
        Member sampleMember = new Member();
        sampleMember.setId(1L);
        sampleMember.setName("John Doe");
        memberRepository.save(sampleMember);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("78912341");
        reservation.setCar(reservedCar);
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.setMember(sampleMember);
        reservationRepository.save(reservation);

        boolean deleteReservedCarResult = carService.deleteCar("11223");
        assertFalse(deleteReservedCarResult);
    }

}
