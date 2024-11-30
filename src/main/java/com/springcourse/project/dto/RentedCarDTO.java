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

        LocalDate pickUpDateTime = reservation.getPickUpDateTime();
        if (pickUpDateTime != null && dropOffDateTime != null) {
            this.reservationDayCount = Period.between(pickUpDateTime, dropOffDateTime).getDays();
        } else {
            this.reservationDayCount = 0; // or handle it as needed
        }
        //this.reservationDayCount = Period.between(reservation.getPickUpDateTime(),dropOffDateTime).getDays();
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(LocalDate dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public int getReservationDayCount() {
        return reservationDayCount;
    }

    public void setReservationDayCount(int reservationDayCount) {
        this.reservationDayCount = reservationDayCount;
    }
}
