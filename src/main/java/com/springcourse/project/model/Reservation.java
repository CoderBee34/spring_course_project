package com.springcourse.project.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Reservation {
    @Id
    @Column(unique = true, length = 8)
    private String reservationNumber;

    @ManyToOne
    private Car car;

    @Column()
    private LocalDate creationDate;
    @Column()
    private LocalDate pickUpDateTime;
    @Column()
    private LocalDate dropOffDateTime;
    @PrimaryKeyJoinColumn
    @ManyToOne
    private Location pickUpLocation;
    @PrimaryKeyJoinColumn
    @ManyToOne
    private Location dropOffLocation;
    @Column()
    private LocalDate returnDate;
    @Column()
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToMany
    private List<Equipment> equipmentList = new ArrayList<>();

    @ManyToMany
    private List<ServiceModel> serviceList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Transient
    private double totalAmount;

    public Reservation(Car car, LocalDate creationDate, LocalDate pickUpDateTime, LocalDate dropOffDateTime, Location pickUpLocation, Location dropOffLocation, LocalDate returnDate, ReservationStatus status, Member member) {
        this.car = car;
        this.reservationNumber = reservationNumberGenerator();
        this.creationDate = creationDate;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.returnDate = returnDate;
        this.status = status;
        this.member = member;
    }

    public Reservation() {
    }

    private String reservationNumberGenerator(){
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            randomString.append(digit);
        }
        return randomString.toString();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Equipment> getEquipmentList() {
        return this.equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<ServiceModel> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceModel> serviceList) {
        this.serviceList = serviceList;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public double getTotalAmount() {
        long timeDiffInDays = Period.between(pickUpDateTime, dropOffDateTime).getDays();
        double totalAmount = timeDiffInDays * car.getDailyPrice();
        for(Equipment equipment : equipmentList)
            totalAmount += equipment.getPrice();
        for (ServiceModel serviceModel : serviceList)
            totalAmount += serviceModel.getPrice();
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber='" + reservationNumber + '\'' +
                ", creationDate=" + creationDate +
                ", pickUpDateTime=" + pickUpDateTime +
                ", dropOffDateTime=" + dropOffDateTime +
                ", pickUpLocation=" + pickUpLocation +
                ", dropOffLocation=" + dropOffLocation +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", member=" + member +
                '}';
    }
}
