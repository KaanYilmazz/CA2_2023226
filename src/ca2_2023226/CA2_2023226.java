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
import java.util.List;
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
        AllTeams _allTeams = FillWithMockData(mockFile);
        String[] AllPlayerList = new String[1000];

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
                        List<ProfessionalPlayer> allProPlayers = combinePlayers(_allTeams.ProTeams);
                        SortPlayers(allProPlayers);
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
        AllTeams allTeams = new AllTeams(); // since I had two types of teams I needed to store them in another class. This way, I can use them everywhere I need.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //I used parser of SimpleDateFormat library for every dates in my models.

        try ( BufferedReader reader = new BufferedReader(new FileReader(mockFile))) { //reader for txt file
            String line;
            int TeamCount = 0; // defining a team counter is the part of the algorith I used for reading from file
            int u19TeamCount = 0;
            while ((line = reader.readLine()) != null) { // loop every line and match data with models.
                String[] parts = line.split(","); // I seperated all attributes by comma
                int modelCheck = Integer.parseInt(parts[0].trim());
                switch (modelCheck) { //In my mock data, I gave datatypes a number. to do so I can seperate my model
                    case 1: // Pro-Team model
                        ProfessionalTeam proTeam = new ProfessionalTeam(); // creating new pro team
                        proTeam.name = parts[1].trim(); // fill with data from txt file
                        proTeam.founded = dateFormat.parse(parts[2].trim());
                        proTeam.area = parts[3].trim();
                        proTeam.league = parts[4].trim();
                        allTeams.ProTeams.add(proTeam);
                        TeamCount++; // every time I created a team I increase the counter.
                        break;
                    case 2: //u19Team model
                        U19Team u19Team = new U19Team(); // creating new u19 team
                        u19Team.name = parts[1]; // fill with data from txt file
                        u19Team.founded = dateFormat.parse(parts[2].trim());
                        u19Team.area = parts[3].trim();
                        u19Team.facilities = ConditionEnum.ConditionGetter(Integer.parseInt(parts[4].trim()));
                        allTeams.u19Teams.add(u19Team);
                        u19TeamCount++;
                        break;
                    case 3: // Professional Player Model
                        ProfessionalPlayer proPlayer = new ProfessionalPlayer(); // creating new pro player
                        proPlayer.name = parts[1].trim(); // fill with data from txt file
                        proPlayer.birthday = dateFormat.parse(parts[2].trim());
                        proPlayer.jerseyNumber = Integer.parseInt(parts[3].trim());
                        proPlayer.position = PositionEnum.PositionGetter(Integer.parseInt(parts[4].trim()));
                        proPlayer.nationality = parts[5].trim();
                        proPlayer.licenceNumber = parts[6].trim();
                        proPlayer.gamesPlayed = Integer.parseInt(parts[7].trim());
                        proPlayer.scores = Integer.parseInt(parts[8].trim());
                        allTeams.ProTeams.get(TeamCount - 1).players.add(proPlayer); // every time I created a player I add them to last team created (through the counter)
                        break;
                    case 4: //u19 Player Model
                        U19Player u19Player = new U19Player(); // creating u19 player
                        u19Player.name = parts[1].trim();
                        u19Player.birthday = dateFormat.parse(parts[2].trim());
                        u19Player.jerseyNumber = Integer.parseInt(parts[3].trim());
                        u19Player.position = PositionEnum.PositionGetter(Integer.parseInt(parts[4].trim()));
                        u19Player.nationality = parts[5].trim();
                        u19Player.Contract = ContractEnum.ContractTypeGetter(Integer.parseInt(parts[6].trim()));
                        allTeams.u19Teams.get(u19TeamCount - 1).players.add(u19Player); // every time I created a player I add them to last team created (through the counter)
                        break;
                    case 5: // Staff Model
                        Staff staff = new Staff(); // creating a staff
                        staff.name = parts[1].trim();
                        staff.job = JobEnum.Jobgetter(Integer.parseInt(parts[2].trim()));
                        if (u19TeamCount == 0) { //in my txt file Proteams always come first. I know its very bad coding but I needed to progress so I just made this up
                            allTeams.ProTeams.get(TeamCount-1).staffList.add(staff); // -1 because of index
                        } else {
                            allTeams.u19Teams.get(u19TeamCount-1).staffList.add(staff); // -1 because of index
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException | ParseException e) {
        } catch (NumberFormatException e) {
            System.err.println(e.toString());
        }

        return allTeams;
    }

    private static void SortPlayers(List<ProfessionalPlayer> proplayerlist) {
        List<String> playerListNames = quickSort(getPlayerNames(proplayerlist));
        for (String str : playerListNames) {
            System.out.println(str);
        }
    }

    public static List<String> quickSort(List<String> list) {
        if (list.size() <= 1) {
            return list;
        }

        String pivot = list.get(list.size() - 1);
        List<String> lesser = quickSortLesser(list, pivot);
        List<String> greater = quickSortGreater(list, pivot);

        lesser.add(pivot);
        lesser.addAll(greater);
        return lesser;
    }

    private static List<String> quickSortLesser(List<String> list, String pivot) {
        List<String> lesser = new java.util.ArrayList<>();
        for (String str : list) {
            if (str.compareTo(pivot) < 0) {
                lesser.add(str);
            }
        }
        return quickSort(lesser);
    }

    private static List<String> quickSortGreater(List<String> list, String pivot) {
        List<String> greater = new java.util.ArrayList<>();
        for (String str : list) {
            if (str.compareTo(pivot) > 0) {
                greater.add(str);
            }
        }
        return quickSort(greater);
    }

    public static List<String> getPlayerNames(List<ProfessionalPlayer> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.name);
        }
        return playerNames;
    }

    private static List<ProfessionalPlayer> combinePlayers(ArrayList<ProfessionalTeam> ProTeams) {
        List<ProfessionalPlayer> allPlayers = new ArrayList<>();
        for (ProfessionalTeam team : ProTeams) {
            allPlayers.addAll(team.players);
        }
        return allPlayers;
    }

}
