package com.springcourse.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {
    @Id
    private int code;
    private String name;
    private String address;

    public Location(int code, String name, String address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public Location() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Location{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
