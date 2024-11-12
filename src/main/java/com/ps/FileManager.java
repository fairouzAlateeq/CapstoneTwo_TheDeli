package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
        public static void saveReceipt(String receiptDetails) {
            // Generate timestamp in the format yyyyMMdd-HHmmss
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String fileName = "receipt_" + timestamp + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(receiptDetails);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

