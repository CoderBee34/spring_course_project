package com.springcourse.project.dto;

public class AdditionalServiceRequestDTO {
    private String reservationNumber;
    private int ServiceId;

    public AdditionalServiceRequestDTO() {
    }

    public AdditionalServiceRequestDTO(String reservationNumber, int serviceId) {
        this.reservationNumber = reservationNumber;
        ServiceId = serviceId;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getServiceId() {
        return ServiceId;
    }

    public void setServiceId(int serviceId) {
        ServiceId = serviceId;
    }
}
