package com.ps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.ps.classes.Sandwich;
import com.ps.classes.Drink;
import com.ps.classes.Chips;
import com.ps.classes.Order;

public class Receipt {

    private LocalDateTime orderDate;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;
    private double totalPrice;
    private String name;

    public Receipt(String name, LocalDateTime orderDate, List<Sandwich> sandwiches, List<Drink> drinks, List<Chips> chips, double totalPrice) {
        this.orderDate = orderDate;
        this.sandwiches = sandwiches;
        this.drinks = drinks;
        this.chips = chips;
        this.totalPrice = totalPrice;
        this.name = name;
    }

    // Format the receipt as a string
    public String formatReceipt() {
        StringBuilder receiptText = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        receiptText.append("Order Date: ").append(orderDate.format(formatter)).append("\n");
        receiptText.append("Customer Name: ").append(name).append("\n"); // Assuming 'name' is set in UserInterface
        receiptText.append("------ Order Details ------\n");

        // Add sandwiches
        if (!sandwiches.isEmpty()) {
            receiptText.append("Sandwiches:\n");
            int sandwichNumber = 1;
            for (Sandwich sandwich : sandwiches) {
                receiptText.append(" Sandwich ").append(sandwichNumber).append(":\n");
                receiptText.append("  Size: ").append(sandwich.getSize()).append("\n");
                receiptText.append("  Bread Type: ").append(sandwich.getBreadType()).append("\n");
                receiptText.append("  Toppings: ").append(sandwich.getTopping()).append("\n"); // Assuming `getToppings` returns a formatted list
                receiptText.append("  Toasted: ").append(sandwich.isToasted() ? "Yes" : "No").append("\n");
                sandwichNumber++;
            }
        }

        // Add drinks
        if (!drinks.isEmpty()) {
            receiptText.append("Drinks:\n");
            for (Drink drink : drinks) {
                receiptText.append(" - ").append(drink.getSize()).append(" ").append(drink.getFlavor()).append("\n");
            }
        }

        // Add chips
        if (!chips.isEmpty()) {
            receiptText.append("Chips:\n");
            for (Chips chip : chips) {
                receiptText.append(" - ").append(chip.getChipsType()).append("\n");
            }
        }

        receiptText.append("--------------------------\n");
        receiptText.append("Total Price: $").append(String.format("%.2f", totalPrice)).append("\n");
        return receiptText.toString();
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
