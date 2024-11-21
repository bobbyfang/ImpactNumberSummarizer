package org.example;

import org.example.numberrangesummarizer.NumberRangeSummarizerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                ---Number Range Summarizer---
                Please select an option from the following:
                1) Provided sample case
                2) Provide custom test case
                3) Read multiple from text file
                0) Exit
                """);
        String option = "";
        while (option.isEmpty() || !option.matches("[0123]")) {
            System.out.println("Enter a valid option.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                option = reader.readLine();
            } catch (IOException e) {
                System.out.println("An error occurred with the input.");
                option = "0";
            }
        }
        NumberRangeSummarizerImpl rs = new NumberRangeSummarizerImpl();
        Collection<Integer> values;

        switch (option){
            case "1":
                values = rs.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
                System.out.println(values);
                System.out.println(rs.summarizeCollection(values));
                break;
            case "2":
                System.out.println("Enter a custom test input.");
                String input = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                     input = reader.readLine();
                } catch (IOException e) {
                    System.out.println("An error occurred with the input.");
                }
                values = rs.collect(input);
                System.out.println(values);
                System.out.println(rs.summarizeCollection(values));
                break;
            case "3":
                try (InputStream inputStream = Main.class.getResourceAsStream("/testInputs.txt")) {
                    if (inputStream == null) {
                        System.out.println("The file 'testInputs.txt' was not found in the resources folder");
                        return;
                    }

                    Scanner scanner = new Scanner(inputStream);
                    while(scanner.hasNextLine()) {
                        values = rs.collect(scanner.nextLine());
                        System.out.println(values);
                        System.out.println(rs.summarizeCollection(values));
                        System.out.println();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Goodbye!");
        }
    }
}