package com.ps;
import com.ps.classes.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
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

        do{
            // to not enter again.
            if (sandwichCommand  == 4) {
                System.out.println("No more sandwiches added.");
                break;
            }

            do{
                System.out.println("what size? 4\", 8\" or 12\"");
                System.out.println("Pick a size: ");
                size = inputScanner.nextInt();
            } while(size != 4 && size != 8 && size !=12);

            // bread choice
            System.out.println("Available bread types:");
            processDispalyBreadTypes();
            inputScanner.nextLine();
            System.out.println("What type of bread?");
            breadType = inputScanner.nextLine().toUpperCase();

            //toasted?
            System.out.println("do you wanted toasted? 1.yes 2. no");
            int toastedCommand = inputScanner.nextInt();
            // toasted to true or false
            boolean toastedChoice;
            if(toastedCommand == 1)
                toastedChoice = true;
            else toastedChoice = false;

            //starting to add toppings
            int toppingCommand;
            do {

                System.out.println("Would you like to add a topping? 1. Yes  2. No");

                toppingCommand = commandScanner.nextInt();

                if (toppingCommand == 1 ) {
                    inputScanner.nextLine();
                    System.out.println("Enter topping name:");

                    String toppingChoiceName = inputScanner.nextLine();

                    // Check if topping is in PremiumTopping or RegularTopping
                    toppingChoice = findToppingByName(toppingChoiceName.toLowerCase());
                    if (toppingChoice != null) {
                        double toppingPrice = toppingChoice.calculatePrice();
                        toppingChoices = new ArrayList<>();
                        toppingChoices.add(toppingChoice);  // Add to toppings list
                        System.out.println(toppingChoice.getName() + " added. Price: $" + toppingPrice);

                        // handling extra
                        System.out.println("Would you like extra Meat? 1. yes 2. no");
                        extraMeatCommand = commandScanner.nextInt();
                        if(extraMeatCommand==1) {
                            extraMeatChoice = true;
                        }
                        else extraMeatChoice = false;
                        System.out.println("Extra cheese? ");
                        extraCheeseCommand = commandScanner.nextInt();
                        if(extraCheeseCommand==1) {
                            extraCheeseChoice = true;
                        }
                        else extraCheeseChoice = false;

                    } else {
                        System.out.println("Invalid topping choice.");
                    }
                } else if (toppingCommand != 2) {
                    System.out.println("Please enter 1 (Yes) or 2 (No).");
                }
            } while (toppingCommand != 2 );

            // saving

            sandwich = new Sandwich(toppingChoices, toastedChoice, Sandwich.BreadTypes.valueOf(breadType), extraMeatChoice, extraCheeseChoice);
            sandwiches.add(sandwich);

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
                System.out.println(drinkFlavor + " added. Price: $" + drink.getPrice());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid flavor. Please choose a valid option.");
            }
        }
        while(sizeChoice !=4);

    }
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

            System.out.println("Another one? 1.yes 2.no");
            chipsCommand = commandScanner.nextInt();
            switch (chipsCommand){
                case 1:
                    processAddChips();
                    break;
                case 2:
                    processStartAnOrder();
                    break;
                default:
                    System.out.println(" 1 or 2 only. ");
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
    private static void processFinishTheOrder(){
        System.out.println("Whats your name? ");
        name = inputScanner.nextLine();

        currentDateTime = LocalDateTime.now();
        System.out.println("your order details: ");
        System.out.println(currentDateTime);
        System.out.println("your sandwiches : ");
        int sandwichNumber = 1;
        for (Sandwich sandwich : sandwiches) {
            System.out.println("\nSandwich " + sandwichNumber + ":");
            System.out.println("Your sandwich:");
            System.out.println("Size: " + sandwich.getSize());
            System.out.println("your bread type: " + sandwich.getBreadType());

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


            sandwichNumber++;
        }
        //calculate total
        for(Sandwich sandwich: sandwiches) {
            totalPrice += sandwich.calculatePrice();
        }
        for(Drink drink:drinks){
            totalPrice += drink.calculatePrice();
        }
        for(Chips bagOfChips: chips){
            totalPrice += bagOfChips.calculatePrice();
        }

        System.out.println("your total is: " + totalPrice);
        order = new Order(name,sandwiches,chips,drinks);
        Receipt receipt = new Receipt(name,currentDateTime, sandwiches, drinks, chips, totalPrice);
        FileManager.saveReceipt(receipt);
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
