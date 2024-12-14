package com.springcourse.project.dto;

public class AvailableCarRequestDTO {
    private String carType;
    private String transmissionType;

    public AvailableCarRequestDTO() {
    }

    public AvailableCarRequestDTO(String carType, String transmissionType) {
        this.carType = carType;
        this.transmissionType = transmissionType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
}
