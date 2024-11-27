package com.springcourse.project;

import com.springcourse.project.dto.CarDTO;
import com.springcourse.project.model.CarType;
import com.springcourse.project.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.AssertionErrors;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class CarServiceTests {
    @Autowired
    CarService carService;

    @Test
    void searchAvailableCarTest(){
        List<CarDTO> carDTOList=  carService.searchAvailableCars("Standard", "Automatic");
        //assertEquals(carDTOList.get(0).getBarcode(),"12345");
        assertNotNull(carDTOList);
    }

}
