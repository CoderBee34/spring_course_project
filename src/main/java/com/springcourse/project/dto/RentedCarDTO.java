package com.springcourse.project.dto;

import com.springcourse.project.model.Car;
import com.springcourse.project.model.CarType;
import com.springcourse.project.model.Location;
import com.springcourse.project.model.Reservation;

import java.time.LocalDate;
import java.time.Period;

public class RentedCarDTO {
    private String barcode;
    private String brand;
    private String model;
    private String transmissionType;
    private CarType type;

    private String reservationNumber;
    private String memberName;
    private LocalDate dropOffDateTime;
    private Location dropOffLocation;
    private int reservationDayCount;

    public RentedCarDTO() {
    }
    public RentedCarDTO(Car car, Reservation reservation){
        this.barcode = car.getBarcode();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.transmissionType = car.getTransmissionType();
        this.type = car.getType();
        this.reservationNumber = reservation.getReservationNumber();
        this.memberName = reservation.getMember().getName();
        this.dropOffDateTime = reservation.getDropOffDateTime();
        this.dropOffLocation = reservation.getDropOffLocation();
        this.reservationDayCount = Period.between(reservation.getPickUpDateTime(),dropOffDateTime).getDays();
    }

    public RentedCarDTO(String barcode, String brand, String model, String transmissionType, CarType type, String reservationNumber, String memberName, LocalDate dropOffDateTime, Location dropOffLocation, int reservationDayCount) {
        this.barcode = barcode;
        this.brand = brand;
        this.model = model;
        this.transmissionType = transmissionType;
        this.type = type;
        this.reservationNumber = reservationNumber;
        this.memberName = memberName;
        this.dropOffDateTime = dropOffDateTime;
        this.dropOffLocation = dropOffLocation;
        this.reservationDayCount = reservationDayCount;
    }
}
