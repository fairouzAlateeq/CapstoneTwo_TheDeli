package com.ps.classes;

import java.util.List;

public class Sandwich implements Product{
    private List<Topping> toppings;
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
    public Sandwich(List<Topping> toppings, boolean isToasted, BreadTypes breadType, boolean extraMeat, boolean extraCheese, int size) {
        this.toppings = toppings;
        this.isToasted = isToasted;
        this.breadType = breadType;
        this.extraMeat = extraMeat;
        this.extraCheese = extraCheese;
        this.size = size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setTopping(List<Topping> topping) {
        this.toppings = toppings;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    @Override
    public double calculatePrice() {
        double basePrice = 0.0;
        switch (size) {
            case 4:
                basePrice = 5.0;
                break;
            case 8:
                basePrice = 7.5;
                break;
            case 12:
                basePrice = 10.0;
                break;
            default:
                break;
        }

        // Add price for toppings
        for (Topping topping : toppings) {
            basePrice += topping.calculatePrice();
        }

        // Additional cost for extra meat and extra cheese based on size
        if (extraMeat) {
            switch (size) {
                case 4:
                    basePrice += 0.50;
                    break;
                case 8:
                    basePrice += 1.00;
                    break;
                case 12:
                    basePrice += 1.50;
                    break;
                default:
                    break;
            }
        }
        if (extraCheese) {
            switch (size) {
                case 4:
                    basePrice += 0.30;
                    break;
                case 8:
                    basePrice += 0.60;
                    break;
                case 12:
                    basePrice += 0.90;
                    break;
                default:
                    break;
            }
        }

        return basePrice;
    }
  //

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

