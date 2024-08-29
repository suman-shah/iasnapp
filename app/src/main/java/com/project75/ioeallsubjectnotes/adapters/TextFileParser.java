package com.project75.ioeallsubjectnotes.adapters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileParser {

    public static List<String> extractSymbolNumbersFromTextFile(File textFile) {
        List<String> symbolNumbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {  // Check if the line is not empty
                    symbolNumbers.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately, e.g., show a message to the user
        }

        return symbolNumbers;
    }

    public static void main(String[] args) {
        // Example usage
        File textFile = new File("path/to/symbol_numbers.txt");  // Replace with your actual file path
        List<String> symbolNumbers = extractSymbolNumbersFromTextFile(textFile);

        System.out.println("List of Symbol Numbers:");
        for (String symbol : symbolNumbers) {
            System.out.println(symbol);
        }
    }
}
