package com.springcourse.project;

import com.springcourse.project.dto.AvailableCarDTO;
import com.springcourse.project.dto.RentedCarDTO;
import com.springcourse.project.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class CarServiceTests {
    @Autowired
    CarService carService;

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
    void returnCarTest(){}

    @Test
    void deleteCarTest(){}

}
