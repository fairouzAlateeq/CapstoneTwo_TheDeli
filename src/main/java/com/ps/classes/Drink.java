package com.ps.classes;

public class Drink implements Product{
     int size;
     double price;
     flavors flavor;
     public enum flavors {
         STRAWBERRY, LEMON, ORIGINAL
     }

    public Drink(int size, flavors flavor) {
        this.size = size;
        this.flavor = flavor;
    }


    @Override
    public double calculatePrice() {
        if(size == 1)
            this.price = 2.00;
        else if(size == 2)
            this.price = 2.50;
        else if(size == 3)
            this.price = 3.00;

        return this.price;
    }



    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public flavors getFlavor() {
        return flavor;
    }

    public void setFlavor(flavors flavor) {
        this.flavor = flavor;
    }
}
