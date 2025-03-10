package com.springcourse.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Equipment {
    @Id
    private int id;
    private String name;
    private double price;

    public Equipment(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Equipment() {
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
        return "Equipment{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
