package com.springcourse.project.dto;

import com.springcourse.project.model.Car;
import com.springcourse.project.model.CarType;

public class CarDTO {
    private String brand;
    private String model;
    private CarType carType;
    private double mileage;
    private String transmissionType;
    private String barcode;

    public CarDTO() {
    }
    public CarDTO(Car car) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.carType = car.getType();
        this.mileage = car.getMileage();
        this.transmissionType = car.getTransmissionType();
        this.barcode = car.getBarcode();
    }

    public CarDTO(String brand, String model, CarType carType, double mileage, String transmissionType, String barcode) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
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

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
