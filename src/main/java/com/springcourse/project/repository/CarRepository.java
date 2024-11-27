package com.springcourse.project.repository;

import com.springcourse.project.model.Car;
import com.springcourse.project.model.CarStatus;
import com.springcourse.project.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
    List<Car> findByStatusAndTypeAndTransmissionType(CarStatus status, CarType type, String transmissionType);
}
