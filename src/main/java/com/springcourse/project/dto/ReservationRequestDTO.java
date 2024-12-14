package com.springcourse.project.dto;

import com.springcourse.project.model.Equipment;
import com.springcourse.project.model.ServiceModel;

import java.util.List;

public class ReservationRequestDTO {
    private String carBarcode;
    private int dayCount;
    private long memberID;
    private int pickUpLocationCode;
    private int dropOffLocationCode;
    private List<Equipment> equipmentList;
    private List<ServiceModel> serviceList;

    public ReservationRequestDTO() {
    }

    public ReservationRequestDTO(String carBarcode, int dayCount, long memberID, int pickUpLocationCode, int dropOffLocationCode, List<Equipment> equipmentList, List<ServiceModel> serviceList) {
        this.carBarcode = carBarcode;
        this.dayCount = dayCount;
        this.memberID = memberID;
        this.pickUpLocationCode = pickUpLocationCode;
        this.dropOffLocationCode = dropOffLocationCode;
        this.equipmentList = equipmentList;
        this.serviceList = serviceList;
    }

    public String getCarBarcode() {
        return carBarcode;
    }

    public void setCarBarcode(String carBarcode) {
        this.carBarcode = carBarcode;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    public int getPickUpLocationCode() {
        return pickUpLocationCode;
    }

    public void setPickUpLocationCode(int pickUpLocationCode) {
        this.pickUpLocationCode = pickUpLocationCode;
    }

    public int getDropOffLocationCode() {
        return dropOffLocationCode;
    }

    public void setDropOffLocationCode(int dropOffLocationCode) {
        this.dropOffLocationCode = dropOffLocationCode;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
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
}
