package com.springcourse.project;

import com.springcourse.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class ReservationServiceTests {
    @Autowired
    ReservationService reservationService;
}
