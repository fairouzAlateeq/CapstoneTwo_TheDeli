package com.ps.classes;

import java.util.Arrays;
import java.util.List;

public class RegularTopping extends Topping{
    public static List<RegularTopping> regularToppings = Arrays.asList(
            // Veggie Toppings
            new RegularTopping("Lettuce", "veggie"),
            new RegularTopping("Tomato", "veggie"),
            new RegularTopping("Onion", "veggie"),
            new RegularTopping("Peppers", "veggies"),
            new RegularTopping("Jalapenos", "veggies"),
            new RegularTopping("Cucumbers", "veggies"),
            new RegularTopping("Pickles", "veggies"),
            new RegularTopping("Guacamole", "veggies"),
            new RegularTopping("Mushrooms", "veggies"),


            // Sauce Toppings
            new RegularTopping("Mayo", "sauce"),
            new RegularTopping("Mustard", "sauce"),
            new RegularTopping("Ketchup", "sauce"),
            new RegularTopping("ranch", "sauce"),
            new RegularTopping("thousand islands","sauce"),
            new RegularTopping("vinaigrette","sauce")
    );
    public RegularTopping(String name, String type) {
        super(name, type, 0.50); // example base price for regular topping
    }
    @Override
    public double calculatePrice(int size) {
        double price = 0.0;
        switch (getName().toLowerCase()) {
            case "lettuce":
            case "tomato":
            case "onion":
                // Regular vegetable pricing
                price = getRegularVegPrice(size);
                break;
            case "mayo":
            case "mustard":
            case "ketchup":
                // Sauce pricing
                price = getSaucePrice(size);
                break;
        }
        return price;
    }
    private double getRegularVegPrice(int size) {
        return 0.50; // Same price for all sizes
    }

    private double getSaucePrice(int size) {
        return 0.20; // Same price for all sizes
    }

    private static List<Topping> getTopping(){

    }


}
