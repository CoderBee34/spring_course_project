package com.springcourse.project.repository;

import com.springcourse.project.model.Car;
import com.springcourse.project.model.CarStatus;
import com.springcourse.project.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
    List<Car> findByStatusAndTypeAndTransmissionType(CarStatus status, CarType type, String transmissionType);

    @Modifying
    @Query("UPDATE Car c SET c.status = ?1 WHERE c.barcode = ?2")
    void updateCarStatusByBarcode(CarStatus status, String barcode);

    @Modifying
    @Query("DELETE Car c WHERE c.barcode = ?1")
    void deleteCarByBarcode(String barcode);

}
