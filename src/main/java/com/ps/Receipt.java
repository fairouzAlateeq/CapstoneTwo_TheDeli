package com.ps;
import com.ps.classes.Sandwich;
import com.ps.classes.Drink;
import com.ps.classes.Chips;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private LocalDateTime orderDate;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;
    private double totalPrice;

    public Receipt() {
        this.orderDate = LocalDateTime.now();
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
        totalPrice += sandwich.calculatePrice();
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
        totalPrice += drink.getPrice();
    }

    public void addChips(Chips chipsItem) {
        chips.add(chipsItem);
        totalPrice += chipsItem.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String formatReceipt() {
        StringBuilder receipt = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        receipt.append("Order Date: ").append(orderDate.format(formatter)).append("\n");
        receipt.append("------ Order Details ------\n");

        // Add sandwiches to receipt
        if (!sandwiches.isEmpty()) {
            receipt.append("Sandwiches:\n");
            for (Sandwich sandwich : sandwiches) {
                receipt.append(sandwich).append("\n");
            }
        }

        // Add drinks to receipt
        if (!drinks.isEmpty()) {
            receipt.append("Drinks:\n");
            for (Drink drink : drinks) {
                receipt.append(drink).append("\n");
            }
        }

        // Add chips to receipt
        if (!chips.isEmpty()) {
            receipt.append("Chips:\n");
            for (Chips chip : chips) {
                receipt.append(chip).append("\n");
            }
        }

        receipt.append("--------------------------\n");
        receipt.append("Total Price: $").append(String.format("%.2f", totalPrice)).append("\n");
        return receipt.toString();
    }
}