package com.springcourse.project;

import com.springcourse.project.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class ReservationServiceTests {
    @Autowired
    ReservationService reservationService;

    @Test
    void makeReservationTest(){
        reservationService.makeReservation("67890",12,1,1,1, null,null);
    }

    @Test
    void cancelReservationTest(){
        boolean result = reservationService.cancelReservation("78912341");
        assertTrue(result);
    }

    @Test
    void deleteReservation(){
        boolean result = reservationService.deleteReservation("78912341");
        assertTrue(result);
    }
}
