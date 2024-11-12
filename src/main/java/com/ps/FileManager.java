package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
    private static LocalDateTime currentDateTime;

    public static void saveReceipt(Receipt receipt) {

        // Format file name based on order date and time
        currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        String fileName = "receipt-" + receipt.getOrderDate().format(formatter) + ".txt";

        // Write receipt information to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(receipt.formatReceipt());
            writer.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt: " + e.getMessage());
        }
    }
    }

