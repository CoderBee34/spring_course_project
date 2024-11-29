package com.springcourse.project.dto;

import com.springcourse.project.model.Location;

import java.util.Date;

public class ReservationDTO {

    private String reservationNumber;
    private Date pickUpDateTime;
    private Date dropOffDateTime;
    private Location pickUpLocation;
    private Location dropOffLocation;
    private double totalAmount;

    public ReservationDTO() {
    }

    public ReservationDTO(String reservationNumber, Date pickUpDateTime, Date dropOffDateTime, Location pickUpLocation, Location dropOffLocation, double totalAmount) {
        this.reservationNumber = reservationNumber;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.totalAmount = totalAmount;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Date getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(Date pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public Date getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(Date dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
