package com.ps.classes;

public class Drink implements Product{
     double price;
    @Override
    public double calculatePrice(int size) {
        return 5.5;
    }

    public double getPrice(int size) {
        if(size == 1)
            this.price = 2.00;
        else if(size == 2)
            this.price = 2.50;
        else if(size == 3)
            this.price = 3.00;

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
