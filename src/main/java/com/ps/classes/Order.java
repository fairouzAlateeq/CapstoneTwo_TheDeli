package com.ps.classes;

import java.util.List;

public class Order implements Product{
    private String customerName;
    private List<Sandwich> sandwiches;
    private List<Chips> chips;
    private List<Drink> drinks;

    public Order(String customerName, List<Sandwich> sandwiches, List<Chips> chips, List<Drink> drinks) {
        this.customerName = customerName;
        this.sandwiches = sandwiches;
        this.chips = chips;
        this.drinks = drinks;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public List<Chips> getChips() {
        return chips;
    }

    public void setChips(List<Chips> chips) {
        this.chips = chips;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public double calculatePrice() {
        double total = 0.0;

        for (Sandwich sandwich : sandwiches) {
            total += sandwich.calculatePrice();
        }

        for (Drink drink : drinks) {
            total += drink.calculatePrice();
        }

        for (Chips chip : chips) {
            total += chip.calculatePrice();
        }

        return total;
    }
}
