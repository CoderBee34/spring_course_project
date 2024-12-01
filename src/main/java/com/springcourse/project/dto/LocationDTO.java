package com.springcourse.project.dto;

import com.springcourse.project.model.Location;

public class LocationDTO {
    private int code;
    private String name;

    public LocationDTO() {
    }

    public LocationDTO(Location location) {
        this.code = location.getCode();
        this.name = location.getName();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
