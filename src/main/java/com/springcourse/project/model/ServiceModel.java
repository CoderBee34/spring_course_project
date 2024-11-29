package com.springcourse.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ServiceModel {
    @Id
    private int id;
    private String name;
    private double price;


    public ServiceModel(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public ServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
