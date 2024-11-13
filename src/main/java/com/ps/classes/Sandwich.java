package com.ps.classes;

import java.util.List;

public class Sandwich implements Product{
    private List<Topping> topping;
    private boolean isToasted;
    private boolean extraMeat;
    private boolean extraCheese;
    double extraMeatPrice;
    double extraCheesePrice;
    double basePrice;

    private double price;

    int size;


    public enum BreadTypes {
        WHITE, WHEAT, RYE, WRAP
    }

    public enum Size {
        SMALL, MEDIUM, LARGE;
    }
    private Sandwich.BreadTypes breadType;
    // Other fields and methods

    public Sandwich.BreadTypes getBreadType() {
        return breadType;
    }

    public int getSize() {
        return size;
    }

    //Consturctor
    public Sandwich(List<Topping> topping, boolean isToasted, BreadTypes breadType, boolean extraMeat, boolean extraCheese, int size) {
        this.topping = topping;
        this.isToasted = isToasted;
        this.breadType = breadType;
        this.extraMeat = extraMeat;
        this.extraCheese = extraCheese;
        this.size = size;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    @Override
    public double calculatePrice() {

        if(size == 4) {
            basePrice = 5.50;
            extraCheesePrice = 0.30;
            extraMeatPrice = 0.50;
        }
        else if(size == 8) {
            basePrice = 7.00;
            extraCheesePrice = 0.60;
            extraMeatPrice = 1.00;
        }
        else {
            basePrice = 8.50;
            extraCheesePrice = 0.90;
            extraMeatPrice = 1.50;
        }

        // Calculate total price by adding the base price with the additional costs for extra meat and extra cheese

        double totalPrice = basePrice;

        for (Topping topping : topping) {
            totalPrice += topping.calculatePrice(); // Sum up all the topping prices
        }

        if(this.extraMeat) {
            totalPrice += extraMeatPrice; // Add extra meat cost if selected
        }

        if(this.extraCheese) {
            totalPrice += extraCheesePrice; // Add extra cheese cost if selected
        }

        return totalPrice; // Return the total price of the sandwich
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public double getExtraMeatPrice() {
        return extraMeatPrice;
    }

    public void setExtraMeatPrice(double extraMeatPrice) {
        this.extraMeatPrice = extraMeatPrice;
    }

    public double getExtraCheesePrice() {
        return extraCheesePrice;
    }

    public void setExtraCheesePrice(double extraCheesePrice) {
        this.extraCheesePrice = extraCheesePrice;
    }
}

