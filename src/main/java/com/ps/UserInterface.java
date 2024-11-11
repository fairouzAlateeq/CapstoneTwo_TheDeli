package com.ps;
import com.ps.classes.Topping;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    // variables
    private static Scanner commandScanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);
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
        while(mainMenuCommand != 0);
    }

    private static void processStartAnOrder(){
       int startCommand;
       do{
           System.out.println("choose an option to start your order:");
           System.out.println("1. Add a Sandwich");
           System.out.println("2. Add a drink");
           System.out.println("3. Add chips");
           System.out.println("4. Finish the order");
           startCommand = commandScanner.nextInt();
           switch (startCommand){
               case 1:
                   //process add a sandwich
                   break;
               case 2:
                   //process add a drink
                   break;
               case 3:
                   //process add chips
               case 4:
                   // display the order
                   // your total is
                   System.out.println("Thank you for choosing our Deli, See you next time!");
                   break;
               default:
                   System.out.println("we only have 3 options");
           }
       }
       while(startCommand!=4);
    }

    private static void processAddASandwich(){
        int sandwichCommand;
        //double price = order.get base price
        do{
            System.out.println("what size?");
            //get size + add ro price
            System.out.println("What type of bread?");
            //get size + add to price
            System.out.println("choose your topping:");
            //display toppings
            //with each topping add its price
            //loop through
            System.out.println("Another Sandwich");
            //case 1 processAddASandwich //case 2 maiMenu // case 3 finish the order



        }
        while();
    }
    private static void processAddADrink(){

    }
    private static void processAddChips(){

    }
    private static void processDisplayToppings(){
        List<Topping> toppings;
    }
    private static void processFinishTheOrder(){

    }

}
