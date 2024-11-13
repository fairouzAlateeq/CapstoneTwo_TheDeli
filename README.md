# 🥪 Deli Order Management System 🥤

## Description: 

The Deli Order Management System is a Java-based console application that simulates a deli ordering experience. This system allows users to customize and place sandwich orders with various options for bread, toppings, drinks, and chips. Designed to be user-friendly, it offers an intuitive ordering process with the ability to view order summaries and save each order as a receipt file.

### Main Classes:

- Order class: View a detailed summary of the order, including total price, and confirm the order at checkout.
- Sandwich class: For sandwich customization.
- Topping abstract class
- PremiumTopping and RegularTopping child classes:  Select from a list of vegetables, meats, and cheeses, with additional charges for premium toppings like extra meat or extra cheese based on sandwich size.
- Drink and Chips classes: 
- Receipt class: Each order is saved as a timestamped .txt file, providing a record of all customer orders.
- Product interface: has the calculatePrice() method. 
- UI class: Manages the user interface and guides the user through order options.
- FileManager class: saves the receipt and creates a file for it.

### How to Use:
- Start by launching the App:
  ![App Launch](C:\Users\fairo\OneDrive\Desktop\Yearup%20courses\Java\java-development\CapstoneTwo_TheDeli\CapstoneTwo_TheDeli\CodeSS\1.png)