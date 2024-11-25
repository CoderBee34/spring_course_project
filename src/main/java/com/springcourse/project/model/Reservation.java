package com.springcourse.project.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class Reservation {
    @Id
    @Column(unique = true, length = 8)
    private String reservationNumber;

    @OneToOne
    private Car car;

    @Column()
    private Date creationDate;
    @Column()
    private Date pickUpDateTime;
    @Column()
    private Date dropOffDateTime;
    @PrimaryKeyJoinColumn
    @OneToOne
    private Location pickUpLocation;
    @PrimaryKeyJoinColumn
    @OneToOne
    private Location dropOffLocation;
    @Column()
    private Date returnDate;
    @Column()
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToMany
    private List<Equipment> equipmentList;

    @ManyToMany
    private List<Service> serviceList;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Reservation(Date creationDate, Date pickUpDateTime, Date dropOffDateTime, Location pickUpLocation, Location dropOffLocation, Date returnDate, ReservationStatus status, Member member) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
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

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
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
