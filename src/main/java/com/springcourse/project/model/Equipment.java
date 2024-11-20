package com.springcourse.project.model;


public class Equipment {
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

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
