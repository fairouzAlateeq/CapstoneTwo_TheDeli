package com.ps.classes;

public class Chips implements Product {
    double price;
    @Override
    public double calculatePrice(int amount) {
        return 0;
    }

    public double getPrice() {
        return 1.50;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
