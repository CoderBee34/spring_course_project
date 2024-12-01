package com.springcourse.project.dto;

import com.springcourse.project.model.Reservation;

import java.time.LocalDate;

public class ReservationDTO {

    private String reservationNumber;
    private LocalDate pickUpDateTime;
    private LocalDate dropOffDateTime;
    private LocationDTO pickUpLocation;
    private LocationDTO dropOffLocation;
    private double totalAmount;

    public ReservationDTO() {
    }
    public ReservationDTO(Reservation reservation){
        this.reservationNumber = reservation.getReservationNumber();
        this.pickUpDateTime = reservation.getPickUpDateTime();
        this.dropOffDateTime = reservation.getDropOffDateTime();
        this.pickUpLocation = new LocationDTO(reservation.getPickUpLocation());
        this.dropOffLocation = new LocationDTO(reservation.getDropOffLocation());
        this.totalAmount = reservation.getTotalAmount();

    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDate getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(LocalDate pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public LocalDate getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(LocalDate dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public LocationDTO getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(LocationDTO pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public LocationDTO getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(LocationDTO dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
