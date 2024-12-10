package com.springcourse.project.dto;

public class AdditionalEquipmentRequestDTO {
    private String reservationNumber;
    private int EquipmentId;

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getEquipmentId() {
        return EquipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        EquipmentId = equipmentId;
    }
}
