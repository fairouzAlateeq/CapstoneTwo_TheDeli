package com.ps;
import com.ps.classes.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static final String RESET = "\u001B[0m";
    // variables
    private static LocalDateTime currentDateTime;
    private static String name;
    private static String drinkFlavor;
    private static Scanner commandScanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);
    private static Sandwich sandwich;
    private static List<Sandwich> sandwiches = new ArrayList<>();
    private static List<Topping> toppingChoices;
    private static List<Chips> chips = new ArrayList<>();
    private static List<Drink> drinks = new ArrayList<>();
    private static String breadType;
    private static int extraMeatCommand;
    private static boolean extraMeatChoice;
    private static int extraCheeseCommand;
    private static boolean extraCheeseChoice;
    private static double totalPrice = 0.0;
    private static int size;
    public static Order order;

    public static void display(){
        showMainMenu();
    }

    public static void showMainMenu() {
        int mainMenuCommand;
        do{
            System.out.println("\033[32m" + "Would you like to start an order?" + RESET );
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

    private static void processStartAnOrder() {
        int startCommand = 0;
            do {
                System.out.println( "choose an option for your order:");
                System.out.println("1. Add a Sandwich");
                System.out.println("2. Add a drink");
                System.out.println("3. Add chips");
                System.out.println("4. Finish the order");
                startCommand = commandScanner.nextInt();
                switch (startCommand) {
                    case 1:
                        processAddASandwich();
                        break;
                    case 2:
                        processAddADrink();
                        break;
                    case 3:
                        processAddChips();
                        break;
                    case 4:
                        processFinishTheOrder();
                        System.out.println("Thank you for choosing our Deli, See you next time!");
                        break;
                    default:
                        System.out.println("we only have 3 options");
                }
            }
            while (startCommand != 4);
    }


    private static void processAddASandwich() {
        int sandwichCommand = 0;
        Topping toppingChoice;
        toppingChoices = new ArrayList<>();

        do {
            if (sandwichCommand == 4) {
                System.out.println("No more sandwiches added.");
                break;
            }

            do {
                System.out.println("what size? 4\", 8\" or 12\"");
                System.out.println("Pick a size: ");
                size = inputScanner.nextInt();
            } while(size != 4 && size != 8 && size != 12);

            // bread choice
            System.out.println("Available bread types:");
            processDispalyBreadTypes();
            inputScanner.nextLine();
            System.out.println("What type of bread?");
            breadType = inputScanner.nextLine().toUpperCase();

            // toasted?
            System.out.println("Do you want it toasted? 1. Yes  2. No");
            int toastedCommand = inputScanner.nextInt();
            boolean toastedChoice = toastedCommand == 1;


            int toppingCommand;
            do {
                System.out.println("Would you like to add a topping? 1. Yes  2. No");
                toppingCommand = commandScanner.nextInt();



                if (toppingCommand == 1) {
                    inputScanner.nextLine();
                    for(Topping topping: PremiumTopping.getPremiumToppings()) {
                        System.out.println(topping.toString() + "\u001B[35m" + "/Premium" + RESET);
                    }
                    for(Topping topping:RegularTopping.getRegularToppings()){
                        System.out.println(topping.toString() + "\u001B[32m" + "/Regular" +RESET);
                    }
                    System.out.println("Enter topping name:");

                    String toppingChoiceName = inputScanner.nextLine();
                    toppingChoice = findToppingByName(toppingChoiceName.toLowerCase());
// Topping != null to set the size and add it to the toppings array
                    if (toppingChoice != null) {
                        double toppingPrice = toppingChoice.calculatePrice();
                        totalPrice += toppingPrice;
                        if (toppingChoice instanceof PremiumTopping) {
                            ((PremiumTopping) toppingChoice).setSize(size); // Set size based on sandwich size
                        }
                        toppingChoices.add(toppingChoice);  // Add to toppings list
                        System.out.println(toppingChoice.getName() + " added");
                    } else {
                        System.out.println("Invalid topping choice.");
                    }
                } else if (toppingCommand != 2) {
                    System.out.println("Please enter 1 (Yes) or 2 (No).");
                }
            } while (toppingCommand != 2);

            // Ask about extra meat and extra cheese only once per sandwich
            System.out.println("Would you like extra meat? 1. Yes  2. No");
            extraMeatCommand = commandScanner.nextInt();
            boolean extraMeatChoice = extraMeatCommand == 1;

            System.out.println("Would you like extra cheese? 1. Yes  2. No");
            extraCheeseCommand = commandScanner.nextInt();
            boolean extraCheeseChoice = extraCheeseCommand == 1;

            // Save the sandwich
                sandwich = new Sandwich(toppingChoices, toastedChoice, Sandwich.BreadTypes.valueOf(breadType), extraMeatChoice, extraCheeseChoice, size);
                sandwiches.add(sandwich);

            System.out.println("Another Sandwich? 1. Yes");
            System.out.println("2. Main Menu");

            sandwichCommand = commandScanner.nextInt();

            switch (sandwichCommand) {
                case 1:
                    processAddASandwich();
                    break;
                case 2:
                    processStartAnOrder();
                    break;
            }
        } while (sandwichCommand != 2);
    }

    private static void processAddADrink(){
        int sizeChoice;
        do{
            System.out.println("What's your drink size?");
            System.out.println("1. Small ($2.00)");
            System.out.println("2. Medium ($2.50)");
            System.out.println("3. Large ($3.00)");
            System.out.println("4. No more drinks");

            sizeChoice = commandScanner.nextInt();

            // so the flavors don't show
            if (sizeChoice == 4) {
                System.out.println("No more drinks added.");
                break;
            }

            System.out.println("Whats your flavor?");
            for (Drink.flavors flavor : Drink.flavors.values()) {
                System.out.println(flavor);
            }

            drinkFlavor = inputScanner.nextLine().trim().toUpperCase();

            //save
            Drink drink;
            try {
                drink = new Drink(sizeChoice, Drink.flavors.valueOf(drinkFlavor));
                drinks.add(drink);
                totalPrice += drink.getPrice();
                System.out.println(drinkFlavor + " added. Price: $" + drink.getPrice());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid flavor. Please choose a valid option.");
            }
        }
        while(sizeChoice !=4);

    }

//    private static void processAddChips(){
//        String chipsChoice;
//        int chipsCommand;
//        do{
//            System.out.println("What chips do you want? ");
//            for (Chips.Types type : Chips.Types.values()){
//                System.out.println(type);
//            }
//            chipsChoice = inputScanner.nextLine().trim().toUpperCase();
//            Chips chipsBag = new Chips(Chips.Types.valueOf(chipsChoice));
//
//            System.out.println("Another one? 1.yes 2.no");
//            chipsCommand = commandScanner.nextInt();
//            switch (chipsCommand){
//                case 1:
//                    processAddChips();
//                    break;
//                case 2:
//                    processStartAnOrder();
//                    break;
//                default:
//                    System.out.println(" 1 or 2 only. ");
//            }
//        }
//        while(chipsCommand != 2);
//
//    }
    private static void processAddChips(){
    String chipsChoice;
    int chipsCommand;
    do{
        System.out.println("What chips do you want? ");
        for (Chips.Types type : Chips.Types.values()){
            System.out.println(type);
        }
        chipsChoice = inputScanner.nextLine().trim().toUpperCase();
        Chips chipsBag = new Chips(Chips.Types.valueOf(chipsChoice));
        chips.add(chipsBag);  // Add chips to the list
        System.out.println(chipsChoice + " chips added.");

        System.out.println("Another one? 1.yes 2.no");
        chipsCommand = commandScanner.nextInt();
        inputScanner.nextLine(); // Consume the newline character after nextInt()
        switch (chipsCommand){
            case 1:
                processAddChips();
                break;
            case 2:
                processStartAnOrder();
                break;
            default:
                System.out.println("1 or 2 only.");
        }
    }
    while(chipsCommand != 2);
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

    private static void processFinishTheOrder() {
        totalPrice = 0.0;

        System.out.println("What's your name? ");
        name = inputScanner.nextLine();

        currentDateTime = LocalDateTime.now();
        System.out.println("Your order details: ");
        System.out.println(currentDateTime);
        System.out.println("Your sandwiches: ");

        int sandwichNumber = 1;
        for (Sandwich sandwich : sandwiches) {
            System.out.println("\nSandwich " + sandwichNumber + ":");
            System.out.println("Size: " + sandwich.getSize());
            System.out.println("Bread type: " + sandwich.getBreadType());

            List<Topping> toppings = sandwich.getToppings();
            if (toppings.isEmpty()) {
                System.out.println("No toppings added.");
            } else {
                for (Topping topping : toppings) {
                    System.out.println("- " + topping.getName());
                    System.out.println(topping.calculatePrice());
                }
            }

            String toasted = sandwich.isToasted() ? "\u001B[33m" + "Toasted" + RESET : "Not Toasted";
            System.out.println("Toasting: " + toasted);

            sandwichNumber++;

            // Add sandwich price to total
            totalPrice += sandwich.calculatePrice();
            System.out.println("total price after sandwich: " + totalPrice);
        }

        // Add drinks price to total
        System.out.println("Drinks: ");
        for (Drink drink : drinks) {
            totalPrice += drink.calculatePrice();
            System.out.println(drink.toString());
            System.out.println("total price after drinks: " + totalPrice);
        }

        // Add chips price to total
        System.out.println("Chips: ");
        for (Chips bagOfChips : chips) {
            totalPrice += bagOfChips.calculatePrice();
            System.out.println(bagOfChips.getChipsType().toString());
            System.out.println("total price after chips: " + totalPrice);
        }

        System.out.println("Your total is: $" + totalPrice);
        System.out.println("name: " + "\u001B[34;1m" + name + RESET);
        System.out.println("CONFIRM? 1. yes 2. no");
        int confirmChoice = commandScanner.nextInt();
        if (confirmChoice == 1) {
            System.out.println("Thank you for choosing our Deli! See you next time!");
        order = new Order(name, sandwiches, chips, drinks);
        Receipt receipt = new Receipt(name, currentDateTime, sandwiches, drinks, chips, totalPrice);
        FileManager.saveReceipt(receipt);
        } else System.out.println("\u001B[91m" + "Canceling..." + RESET);


        System.exit(0);
    }
//    private static void processFinishTheOrder() {
//        // Reset totalPrice to zero before recalculating
//        totalPrice = 0.0;
//
//        System.out.println("What's your name? ");
//        name = inputScanner.nextLine();
//
//        currentDateTime = LocalDateTime.now();
//        System.out.println("Your order details: ");
//        System.out.println(currentDateTime);
//        System.out.println("Your sandwiches: ");
//        int sandwichNumber = 1;
//        for (Sandwich sandwich : sandwiches) {
//            System.out.println("\nSandwich " + sandwichNumber + ":");
//            System.out.println("Size: " + sandwich.getSize());
//            System.out.println("Bread type: " + sandwich.getBreadType());
//
//            List<Topping> toppings = sandwich.getTopping();
//            if (toppings.isEmpty()) {
//                System.out.println("No toppings added.");
//            } else {
//                for (Topping topping : toppings) {
//                    System.out.println("- " + topping.getName());
//                }
//            }
//
//            String toasted = sandwich.isToasted() ? "Toasted" : "Not Toasted";
//            System.out.println("Toasting: " + toasted);
//
//            sandwichNumber++;
//
//        }
//    // Calculate the total price once
//    for (Sandwich sandwich : sandwiches) {
//        totalPrice += sandwich.calculatePrice();
//    }
//    for (Drink drink : drinks) {
//        totalPrice += drink.calculatePrice();
//    }
//    for (Chips bagOfChips : chips) {
//        totalPrice += bagOfChips.calculatePrice();
//    }
//
//    System.out.println("Your total is: $" + totalPrice);
//
//    order = new Order(name, sandwiches, chips, drinks);
//    Receipt receipt = new Receipt(name, currentDateTime, sandwiches, drinks, chips, totalPrice);
//    FileManager.saveReceipt(receipt);
//        System.exit(0);
//}

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
        return null;
    }
}
