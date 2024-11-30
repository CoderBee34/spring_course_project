package com.springcourse.project;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.model.Car;
import com.springcourse.project.model.CarStatus;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.model.ReservationStatus;
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

    @Test
    void searchAvailableCarTest(){
        List<AvailableCarDTO> carDTOList = carService.searchAvailableCars("Standard", "Automatic");
        assertNotNull(carDTOList);
    }

    @Test
    void getRentedCarsTest(){
        List<RentedCarDTO> rentedCarDTOList = carService.getRentedCars();
        RentedCarDTO obj = rentedCarDTOList.get(0);
        assertEquals(obj.getBarcode(), "12345");

    }

    @Test
    void returnCarTest(){
        String sampleReservationId = "78912341";
        Optional<Reservation> sampleReservationOptional = reservationRepository.findById(sampleReservationId);
        Reservation sampleReservation = sampleReservationOptional.get();
        Car sampleCar = sampleReservation.getCar();

        assertEquals(sampleCar.getStatus(), CarStatus.LOANED);
        assertEquals(sampleReservation.getStatus(), ReservationStatus.PENDING);

        carService.returnCar(sampleReservationId);

        sampleReservationOptional = reservationRepository.findById(sampleReservationId);
        sampleReservation = sampleReservationOptional.get();
        sampleCar = sampleReservation.getCar();

        assertEquals(sampleCar.getStatus(), CarStatus.AVAILABLE);
        assertEquals(sampleReservation.getStatus(), ReservationStatus.COMPLETED);

    }

    @Test
    void deleteCarTest(){
        boolean result1 = carService.returnCar("78912341");
        carService.deleteCar("12345");
    }

}
