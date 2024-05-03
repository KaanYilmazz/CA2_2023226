/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2_2023226;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author kaany
 */
enum MenuChoice {
    SORT_PLAYERS,
    SORT_TEAMS,
    SORT_COACHES,
    SEARCH_PLAYERS_BY_NAME,
    SEARCH_TEAMS_BY_NAME,
    SEARCH_COACHES_BY_NAME,
    ADD_PLAYER,
    GENERATE_RANDOM_PLAYER,
    EXIT
}

class AllTeams {

    ArrayList<ProfessionalTeam> ProTeams;
    ArrayList<U19Team> u19Teams;

    public AllTeams() {
        this.ProTeams = new ArrayList<>();
        this.u19Teams = new ArrayList<>();
    }
}

public class CA2_2023226 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner _scanner = new Scanner(System.in);
        System.out.println("Enter the File name .txt extention: ");
        String mockFile = _scanner.next();
        AllTeams allTeams = FillWithMockData(mockFile);

        try ( Scanner scanner = new Scanner(System.in)) {
            MenuChoice choice;
            do {
                // Print the menu
                System.out.println("***** WELCOME TO RUGBY DATABASE *****");
                System.out.println("1. Sort Players");
                System.out.println("2. Sort Teams");
                System.out.println("3. Sort Coaches");
                System.out.println("4. Search Players by Name");
                System.out.println("5. Search Teams by Name");
                System.out.println("6. Search Coaches by Name");
                System.out.println("7. Add Player");
                System.out.println("8. Generate Random Player");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");

                // Take user input
                int userInput = scanner.nextInt();

                // Convert user input to enum
                choice = getMenuChoice(userInput);

                // Perform the selected operation
                switch (choice) {
                    case SORT_PLAYERS:
                        System.out.println("Add contact operation selected.");
                        // Here would be the code for adding a contact
                        break;
                    case SORT_TEAMS:
                        System.out.println("Search contact operation selected.");
                        // Here would be the code for searching a contact
                        break;
                    case SORT_COACHES:
                        System.out.println("Delete contact operation selected.");
                        // Here would be the code for deleting a contact
                        break;
                    case SEARCH_PLAYERS_BY_NAME:
                        System.out.println("Delete contact operation selected.");
                        // Here would be the code for deleting a contact
                        break;
                    case SEARCH_TEAMS_BY_NAME:
                        System.out.println("Delete contact operation selected.");
                        // Here would be the code for deleting a contact
                        break;
                    case SEARCH_COACHES_BY_NAME:
                        System.out.println("Delete contact operation selected.");
                        // Here would be the code for deleting a contact
                        break;
                    case ADD_PLAYER:
                        System.out.println("Delete contact operation selected.");
                        // Here would be the code for deleting a contact
                        break;
                    case GENERATE_RANDOM_PLAYER:
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
                return MenuChoice.SORT_PLAYERS;
            case 2:
                return MenuChoice.SORT_TEAMS;
            case 3:
                return MenuChoice.SORT_COACHES;
            case 4:
                return MenuChoice.SEARCH_PLAYERS_BY_NAME;
            case 5:
                return MenuChoice.SEARCH_TEAMS_BY_NAME;
            case 6:
                return MenuChoice.SEARCH_COACHES_BY_NAME;
            case 7:
                return MenuChoice.ADD_PLAYER;
            case 8:
                return MenuChoice.GENERATE_RANDOM_PLAYER;
            case 9:
                return MenuChoice.EXIT;
            default:
                return null;
        }

    }

    private static AllTeams FillWithMockData(String mockFile) {
        AllTeams allTeams = new AllTeams();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try ( BufferedReader reader = new BufferedReader(new FileReader(mockFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int modelCheck = Integer.parseInt(parts[0].trim());
                switch (modelCheck) { //In my mock data I gave datatypes a number.
                    case 1: // Pro-Team model
                        ProfessionalTeam proTeam = new ProfessionalTeam();
                        proTeam.name = parts[1];
                        proTeam.founded = dateFormat.parse(parts[2].trim());
                        proTeam.area = parts[3].trim();
                        ;
                        proTeam.league = parts[4].trim();
                        ;
                        allTeams.ProTeams.add(proTeam);
                    default:
                        break;
                }
            }
        } catch (IOException | ParseException e) {
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in the file.");
        }

        return allTeams;
    }
}
