package com.ps;
import com.ps.classes.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    // variables
    private static String name;
    private static String drinkFlavor;
    private static Scanner commandScanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);
    private static Sandwich sandwich;
    private static List<Sandwich> sandwiches = new ArrayList<>();
    private static List<Topping> toppingChoices = new ArrayList<>();
    private static int chipsCounter = 0;
    private static List<Drink> drinks = new ArrayList<>();
    private static String breadType;
    private static double orderPrice = 0;
    public static void display(){
        showMainMenu();
    }

    public static void showMainMenu() {
        int mainMenuCommand;
        do{
            System.out.println("Would you like to start an order?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            mainMenuCommand = commandScanner.nextInt();
            switch (mainMenuCommand){
                case 1:
                    processStartAnOrder();
                    break;
                case 2:
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("1 or 2.");
            }
        }
        while(mainMenuCommand != 2);
    }

    private static void processStartAnOrder(){
       int startCommand;
       do{
           System.out.println("choose an option for your order:");
           System.out.println("1. Add a Sandwich");
           System.out.println("2. Add a drink");
           System.out.println("3. Add chips");
           System.out.println("4. Finish the order");
           startCommand = commandScanner.nextInt();
           switch (startCommand){
               case 1:
                   processAddASandwich();
                   break;
               case 2:
                   processAddADrink();
                   break;
               case 3:
                   processAddChips();
               case 4:
                   processFinishTheOrder();
                   System.out.println("Thank you for choosing our Deli, See you next time!");
                   break;
               default:
                   System.out.println("we only have 3 options");
           }
       }
       while(startCommand!=4);
    }

    private static void processAddASandwich(){
        int sandwichCommand = 0;
        Topping toppingChoice;
        int size;
        //double price = order.get base price
        do{
            System.out.println("what size? 4\", 8\" or 12\"");
            do{
                System.out.println("Pick a size: ");
                size = inputScanner.nextInt();
            }while(size != 4 && size != 8 && size !=12);


            if(size==4)
                orderPrice += orderPrice + Sandwich.Size.SMALL.getPrice();
            else if(size ==8)
                orderPrice += orderPrice + Sandwich.Size.MEDIUM.getPrice();
            else if(size ==12)
                orderPrice += orderPrice + Sandwich.Size.SMALL.getPrice();

            System.out.println("Available bread types:");
            processDispalyBreadTypes();
            inputScanner.nextLine();
            System.out.println("What type of bread?");
            breadType = inputScanner.nextLine();
            System.out.println("do you wanted toasted? 1.yes 2. no");
            int toastedCommand = inputScanner.nextInt();
            boolean toastedChoice;
            if(toastedCommand == 1)
                toastedChoice = true;
            else toastedChoice = false;

            int toppingCommand;
            do {

                System.out.println("Would you like to add a topping? 1. Yes  2. No");

                toppingCommand = commandScanner.nextInt();

                if (toppingCommand == 1 ) {
                    processDisplayToppings();  // Display available toppings
                    inputScanner.nextLine();
                    System.out.println("Enter topping name:");

                    String toppingChoiceName = inputScanner.nextLine();

                    // Check if topping is in PremiumTopping or RegularTopping
                    toppingChoice = findToppingByName(toppingChoiceName.toLowerCase());
                    if (toppingChoice != null) {
                        double toppingPrice = toppingChoice.calculatePrice(size);
                        orderPrice += toppingPrice;
                        toppingChoices.add(toppingChoice);  // Add to selected toppings list

                        System.out.println(toppingChoice.getName() + " added. Price: $" + toppingPrice);
                    } else {
                        System.out.println("Invalid topping choice.");
                    }
                } else if (toppingCommand != 2) {
                    System.out.println("Please enter 1 (Yes) or 2 (No).");
                }
            } while (toppingCommand != 2 );

            // toast?
            System.out.println("Another Sandwich? 1. yes");
            System.out.println("2. Main Menu");
            System.out.println("3. Finish the order");
            sandwichCommand = commandScanner.nextInt();
            switch (sandwichCommand){
                //case 1 processAddASandwich //case 2 maiMenu // case 3 finish the order
                case 1:
                    processAddASandwich();
                    break;
                case 2:
                    processStartAnOrder();
                    break;
                case 3:
                    sandwich = new Sandwich(toppingChoices, toastedChoice, breadType);
                    sandwiches.add(sandwich);
                    processFinishTheOrder();
                    break;
            }
        }
        while(sandwichCommand!= 3);
    }
    private static void processAddADrink(){
        int sizeChoice;
        do{
            System.out.println("What's your drink size?");
            System.out.println("1. Small ($1.50)");
            System.out.println("2. Medium ($2.00)");
            System.out.println("3. Large ($2.50)");
            System.out.println("4. No more drinks");

            sizeChoice = commandScanner.nextInt();
            System.out.println("Whats your flavor?");
            for (Drink.flavors flavor : Drink.flavors.values()) {
                System.out.println(flavor);
            }
            drinkFlavor = inputScanner.nextLine();
            Drink drink;
            drinks.add(drink = new Drink (sizeChoice, drinkFlavor));
            orderPrice += drink.getPrice();

        }
        while(sizeChoice !=4 );

    }
    private static void processAddChips(){
        chipsCounter++;
    }
    private static void processDisplayToppings(){
        System.out.println("Premium Toppings:");
        for (PremiumTopping topping : PremiumTopping.getPremiumToppings()) {
            System.out.println(topping.getName());
        }
        System.out.println("\nRegular Toppings:");
        for (RegularTopping topping : RegularTopping.getRegularToppings()) {
            System.out.println(topping.getName());
        }
    }
    private static void processFinishTheOrder(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("your order details: ");
        System.out.println(currentDateTime);
        System.out.println("your sandwich has: ");
        int sandwichNumber = 1;
        for (Sandwich sandwich : sandwiches) {
            System.out.println("\nSandwich " + sandwichNumber + ":");
            System.out.println("Bread type: " + breadType);
            System.out.println("Your sandwich has:");

            List<Topping> toppings = sandwich.getTopping();
            if (toppings.isEmpty()) {
                System.out.println("No toppings added.");
            } else {
                for (Topping topping : toppings) {
                    System.out.println("- " + topping.getName());
                }
            }

            String toasted = sandwich.isToasted() ? "Toasted" : "Not Toasted";
            System.out.println("Your sandwich is: " + toasted);
            System.out.println("your bread type: " + sandwich.getBreadType());

            sandwichNumber++;
        }
        System.out.println("your total is: " + orderPrice);
    }
    public static void processDispalyBreadTypes(){
        for (Sandwich.BreadTypes bread : Sandwich.BreadTypes.values()) {
            System.out.println(bread);
        }

    }
    private static Topping findToppingByName(String name) {
        for (PremiumTopping premiumTopping : PremiumTopping.getPremiumToppings()) {
            if (premiumTopping.getName().equalsIgnoreCase(name)) {
                return premiumTopping;
            }
        }
        for (RegularTopping regularTopping : RegularTopping.getRegularToppings()) {
            if (regularTopping.getName().equalsIgnoreCase(name)) {
                return regularTopping;
            }
        }
        return null;  // Return null if no topping found
    }

}
