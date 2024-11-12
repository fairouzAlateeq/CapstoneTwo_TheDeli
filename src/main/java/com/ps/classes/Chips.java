package com.ps.classes;

public class Chips implements Product {
    double price;
    public enum Types {
        BUGLES, KETTLE
    }
    Types type;

    public Chips(Types type) {
        this.price = 1.50;
        this.type = type;
    }

    @Override
    public double calculatePrice() {
        return 1.50;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Chips.Types getChipsType() {
        return type;
    }

}
