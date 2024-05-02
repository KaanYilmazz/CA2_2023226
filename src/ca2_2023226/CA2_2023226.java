/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2_2023226;

import java.util.Scanner;

/**
 *
 * @author kaany
 */

enum MenuChoice {
    SORT,
    SEARCH,
    DELETE_CONTACT,
    EXIT
}

public class CA2_2023226 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MenuChoice choice;
            do {
                // Print the menu
                System.out.println("***** PHONE DIRECTORY *****");
                System.out.println("1. Add Contact");
                System.out.println("2. Search Contact");
                System.out.println("3. Delete Contact");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                // Take user input
                int userInput = scanner.nextInt();
                
                // Convert user input to enum
                choice = getMenuChoice(userInput);
                
                // Perform the selected operation
                switch (choice) {
                    case ADD_CONTACT:
                        System.out.println("Add contact operation selected.");
                        // Here would be the code for adding a contact
                        break;
                    case SEARCH_CONTACT:
                        System.out.println("Search contact operation selected.");
                        // Here would be the code for searching a contact
                        break;
                    case DELETE_CONTACT:
                        System.out.println("Delete contact operation selected.");
                        // Here would be the code for deleting a contact
                        break;
                    case EXIT:
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != MenuChoice.EXIT);
        }
    }
    
    private static MenuChoice getMenuChoice(int userInput) {
        switch (userInput) {
            case 1:
                return MenuChoice.SEARCH;
            case 2:
                return MenuChoice.SORT;
            case 3:
                return MenuChoice.DELETE_CONTACT;
            case 4:
                return MenuChoice.EXIT;
            default:
                return null;
        }
    
}
