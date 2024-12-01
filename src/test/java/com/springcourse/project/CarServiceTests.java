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
        String sampleReservationId = "78912341";

        Optional<Reservation> sampleReservationOptional = reservationRepository.findById(sampleReservationId);
        Reservation sampleReservation = sampleReservationOptional.get();
        Car sampleCar = sampleReservation.getCar();

        assertEquals(sampleCar.getStatus(), CarStatus.LOANED);
        assertEquals(sampleReservation.getStatus(), ReservationStatus.CONFIRMED);

        boolean result = carService.returnCar(sampleReservationId);

        sampleReservationOptional = reservationRepository.findById(sampleReservationId);
        sampleReservation = sampleReservationOptional.get();
        sampleCar = sampleReservation.getCar();

        assertTrue(result);
        assertEquals(sampleCar.getStatus(), CarStatus.AVAILABLE);
        assertEquals(sampleReservation.getStatus(), ReservationStatus.COMPLETED);

    }

    @Test
    void deleteCarTest() {
        String sampleCarBarcode = "12345";

        // Ensure the car is available
        Optional<Car> carOptional = carRepository.findById(sampleCarBarcode);
        assertTrue(carOptional.isPresent());
        Car sampleCar = carOptional.get();
        sampleCar.setStatus(CarStatus.AVAILABLE);
        carRepository.save(sampleCar);

        // Ensure there are no reservations for the car
        reservationRepository.deleteAll(reservationRepository.findReservationsByCarBarcode(sampleCarBarcode));

        // Delete the car
        boolean deleteResult = carService.deleteCar(sampleCarBarcode);
        assertTrue(deleteResult);

        // Verify the car is deleted
        carOptional = carRepository.findById(sampleCarBarcode);
        assertFalse(carOptional.isPresent());
    }

}
