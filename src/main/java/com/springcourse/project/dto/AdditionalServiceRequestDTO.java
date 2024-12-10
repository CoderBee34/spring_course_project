package com.springcourse.project.dto;

public class AdditionalServiceRequestDTO {
    private String reservationNumber;
    private int ServiceId;

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
