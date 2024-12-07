package com.springcourse.project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(version = "1.0", title = "Car Rental System API"))
public class ProjectApplication {

    // java 23 ve Derby database'e ihtiyacınız olucak.
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
