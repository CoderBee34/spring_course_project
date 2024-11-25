package com.springcourse.project.model;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    private String barcode;
    private String licensePlate;
    private int passengerCapacity;
    private String brand;
    private String model;
    private double mileage;
    private String transmissionType;
    private double dailyPrice;
    @Column()
    @Enumerated(EnumType.STRING)
    private CarType type;
    @Column()
    @Enumerated(EnumType.STRING)
    private CarStatus status;

    public Car(String barcode, String licensePlate, int passengerCapacity, String brand, String model, double mileage, String transmissionType, double dailyPrice, CarType type, CarStatus carStatus) {
        this.barcode = barcode;
        this.licensePlate = licensePlate;
        this.passengerCapacity = passengerCapacity;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.type = type;
        this.status = carStatus;
    }

    public Car() {
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
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

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public CarStatus getStatus() {
        return status;
    }


    public void setStatus(CarStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "barcode='" + barcode + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", passengerCapacity=" + passengerCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                ", transmissionType='" + transmissionType + '\'' +
                ", dailyPrice=" + dailyPrice +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
