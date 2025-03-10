package com.springcourse.project;

import com.springcourse.project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectApplication.class)
@ActiveProfiles("test")
public class LocationServiceTests {
    @Autowired
    LocationService locationService;
}
