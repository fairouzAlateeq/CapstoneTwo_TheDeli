package com.ps.classes;

import java.util.Arrays;
import java.util.List;

public class PremiumTopping extends Topping{
    private int size;
    public static List<PremiumTopping> premiumToppings = Arrays.asList(
            new PremiumTopping("Steak", "Meat"),
            new PremiumTopping("Ham", "Meat"),
            new PremiumTopping("Salami", "Meat"),
            new PremiumTopping("Roast Beef", "Meat"),
            new PremiumTopping("Chicken", "Meat"),
            new PremiumTopping("Bacon", "Meat"),
            new PremiumTopping("American", "Cheese"),
            new PremiumTopping("Provolone", "Cheese"),
            new PremiumTopping("Cheddar", "Cheese"),
            new PremiumTopping("Swiss", "Cheese")
    );

    public PremiumTopping(String name, String type) {
        super(name, type, 0); // Base price is 0, actual price is calculated by size
    }
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        double price = 0;
        switch (getName().toLowerCase()) {
            case "steak":
            case "ham":
            case "salami":
            case "roast beef":
            case "chicken":
            case "bacon":
                // Meat pricing
                price = getMeatPrice();
                break;
            case "american":
            case "provolone":
            case "cheddar":
            case "swiss":
                // Cheese pricing
                price = getCheesePrice();
                break;
        }
        return price;
    }

    public double getMeatPrice() {

        switch (size) {
            case 4: return 1.00;
            case 8: return 2.00;
            case 12: return 3.00;
            default: return 0;
        }
    }

    public double getCheesePrice() {
        switch (size) {
            case 4:
                return 0.75;
            case 8:
                return 1.50;
            case 12:
                return 2.25;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return getName() + " (" + getType() + ")";
    }
    public static List<PremiumTopping> getPremiumToppings(){
        return premiumToppings;
    }
}
