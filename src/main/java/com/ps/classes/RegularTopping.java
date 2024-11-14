package com.ps.classes;

import java.util.Arrays;
import java.util.List;

public class RegularTopping extends Topping{
    public static List<RegularTopping> regularToppings = Arrays.asList(
            // Veggie Toppings
            new RegularTopping("Lettuce", "Veggie"),
            new RegularTopping("Tomato", "Veggie"),
            new RegularTopping("Onion", "Veggie"),
            new RegularTopping("Peppers", "Veggies"),
            new RegularTopping("Jalapenos", "Veggies"),
            new RegularTopping("Cucumbers", "Veggies"),
            new RegularTopping("Pickles", "Veggies"),
            new RegularTopping("Guacamole", "Veggies"),
            new RegularTopping("Mushrooms", "Veggies"),


            // Sauce Toppings
            new RegularTopping("Mayo", "Sauce"),
            new RegularTopping("Mustard", "Sauce"),
            new RegularTopping("Ketchup", "Sauce"),
            new RegularTopping("Ranch", "Sauce"),
            new RegularTopping("Thousand islands","Sauce"),
            new RegularTopping("Vinaigrette","Sauce")
    );
    // constructor
    public RegularTopping(String name, String type) {
        super(name, type, 0.50); // example base price for regular topping
    }

    @Override
    public double calculatePrice() {
        return 0.0;
    }
    @Override
    public String toString() {
        return getName() + " (" + getType() + ")";
    }
    public static List<RegularTopping> getRegularToppings(){
        return regularToppings;
    }


}
