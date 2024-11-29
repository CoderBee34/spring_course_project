package com.springcourse.project;

import com.springcourse.project.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class EquipmentServiceTests {
    @Autowired
    EquipmentService equipmentService;

    @Test
    void addEquipmentToReservationTest(){
        boolean result = equipmentService.addEquipmentToReservation("78912341",2);
        assertTrue(result);
    }
}
