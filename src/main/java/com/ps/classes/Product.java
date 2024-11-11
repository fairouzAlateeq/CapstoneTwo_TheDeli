package com.ps.classes;

public interface Product {
    double calculatePrice(int size);
    default double getBasePrice() {
        return 5.50; // base price
    }
}
