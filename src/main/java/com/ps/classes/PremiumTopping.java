package com.ps.classes;

import java.util.Arrays;
import java.util.List;

public class PremiumTopping extends Topping{

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

    @Override
    public double calculatePrice(int size) {
        double price = 0;
        switch (getName().toLowerCase()) {
            case "steak":
            case "ham":
            case "salami":
            case "roast beef":
            case "chicken":
            case "bacon":
                // Meat pricing
                price = getMeatPrice(size);
                break;
            case "american":
            case "provolone":
            case "cheddar":
            case "swiss":
                // Cheese pricing
                price = getCheesePrice(size);
                break;
        }
        return price;
    }

    public double getMeatPrice(int size) {
        switch (size) {
            case 4: return 1.00;
            case 8: return 2.00;
            case 12: return 3.00;
            default: return 0;
        }
    }

    public double getCheesePrice(int size) {
        switch (size) {
            case 4:
                return 0.30;
            case 8:
                return 0.60;
            case 12:
                return 0.90;
            default:
                return 0;
        }
    }

    public static List<PremiumTopping> getPremiumToppings(){
        return premiumToppings;
    }
}
