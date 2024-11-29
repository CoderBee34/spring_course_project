package com.springcourse.project;

import com.springcourse.project.service.ServiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class ServiceServiceTests {
    @Autowired
    ServiceService serviceService;

    @Test
    void addServiceToReservation(){
        serviceService.addServiceToReservation("78912341",1);
    }

}
