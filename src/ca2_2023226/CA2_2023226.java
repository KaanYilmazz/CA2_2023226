/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2_2023226;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author kaany
 */
enum MenuChoice {
    LIST_ALL,
    SORT_PLAYERS,
    SORT_TEAMS,
    SORT_STAFF,
    SEARCH_PLAYERS_BY_NAME,
    SEARCH_TEAMS_BY_NAME,
    SEARCH_STAFF_BY_NAME,
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
     * @throws java.io.IOException
     */
    static AllTeams _allTeams;
    static ArrayList<String> randomNames;

    public static void main(String[] args) throws IOException {

        Scanner _scanner = new Scanner(System.in);
        System.out.println("Enter the File name .txt extention: ");
        String mockFile = _scanner.next();
        _allTeams = FillWithMockData(mockFile);
        String userInputToSearch;
        randomNames = fillRandomNames();

        try ( Scanner scanner = new Scanner(System.in)) {
            MenuChoice choice;
            do {
                // Print the menu
                System.out.println("\n***** WELCOME TO RUGBY DATABASE *****");
                System.out.println("1. List All");
                System.out.println("2. Sort Players");
                System.out.println("3. Sort Teams");
                System.out.println("4. Sort Staff");
                System.out.println("5. Search Players by Name");
                System.out.println("6. Search Teams by Name");
                System.out.println("7. Search Staff by Name");
                System.out.println("8. Add Player");
                System.out.println("9. Generate Random Player");
                System.out.println("10. Exit");

                // Take user input
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter your choice please: ");
                int userInput = Integer.parseInt(input.readLine());
                // Convert user input to enum
                choice = getMenuChoice(userInput);
                List<ProfessionalPlayer> allProPlayers = combinePlayers(_allTeams.ProTeams);
                List<U19Player> u19Players = combineU19Players(_allTeams.u19Teams);
                List<Staff> allStaff = CombineStaff(_allTeams.ProTeams, _allTeams.u19Teams);
                // Perform the selected operation
                switch (choice) {
                    case LIST_ALL:
                        ListAll(_allTeams);
                        break;
                    case SORT_PLAYERS:
                        SortPlayers(allProPlayers, u19Players);
                        break;
                    case SORT_TEAMS:
                        SortTeams(_allTeams);
                        break;
                    case SORT_STAFF:
                        SortStaff(allStaff);
                        break;
                    case SEARCH_PLAYERS_BY_NAME:
                        System.out.println("\n Please enter the name of the player you wish to find:");
                        userInputToSearch = scanner.nextLine();
                        SearchPlayers(userInputToSearch, allProPlayers, u19Players);
                        break;
                    case SEARCH_TEAMS_BY_NAME:
                        System.out.println("\n Please enter the name of the team you wish to find:");
                        userInputToSearch = scanner.nextLine();
                        SearchTeam(userInputToSearch, _allTeams);
                        break;
                    case SEARCH_STAFF_BY_NAME:
                        System.out.println("\n Please enter the name of the team you wish to find:");
                        userInputToSearch = scanner.nextLine();
                        SearchStaff(userInputToSearch, allStaff);
                        break;
                    case ADD_PLAYER:
                        AddPlayer();
                        break;
                    case GENERATE_RANDOM_PLAYER:
                        GenerateRandomPlayer();
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
                return MenuChoice.LIST_ALL;
            case 2:
                return MenuChoice.SORT_PLAYERS;
            case 3:
                return MenuChoice.SORT_TEAMS;
            case 4:
                return MenuChoice.SORT_STAFF;
            case 5:
                return MenuChoice.SEARCH_PLAYERS_BY_NAME;
            case 6:
                return MenuChoice.SEARCH_TEAMS_BY_NAME;
            case 7:
                return MenuChoice.SEARCH_STAFF_BY_NAME;
            case 8:
                return MenuChoice.ADD_PLAYER;
            case 9:
                return MenuChoice.GENERATE_RANDOM_PLAYER;
            case 10:
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
                            allTeams.ProTeams.get(TeamCount - 1).staffList.add(staff); // -1 because of index
                        } else {
                            allTeams.u19Teams.get(u19TeamCount - 1).staffList.add(staff); // -1 because of index
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

    private static void SortPlayers(List<ProfessionalPlayer> proplayerlist, List<U19Player> u19playerlist) {
        System.out.println("Professional Players List by Name\n");
        List<String> playerListNames = quickSort(getPlayerNames(proplayerlist));
        for (String str : playerListNames) {
            System.out.println(str);
        }
        System.out.println("\n U19 Players List by Name\n");
        List<String> U19playerListNames = quickSort(getU19PlayerNames(u19playerlist));
        for (String str : U19playerListNames) {
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

    private static List<ProfessionalPlayer> combinePlayers(ArrayList<ProfessionalTeam> Teams) {
        List<ProfessionalPlayer> allPlayers = new ArrayList<>();
        for (ProfessionalTeam team : Teams) {
            allPlayers.addAll(team.players);
        }
        return allPlayers;
    }

    public static List<String> getU19PlayerNames(List<U19Player> players) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.name);
        }
        return playerNames;
    }

    private static List<U19Player> combineU19Players(ArrayList<U19Team> Teams) {
        List<U19Player> allPlayers = new ArrayList<>();
        for (U19Team team : Teams) {
            allPlayers.addAll(team.players);
        }
        return allPlayers;
    }

    private static void SortTeams(AllTeams _allTeams) {
        List<String> proTeamNames = _allTeams.ProTeams.stream()
                .map(ProfessionalTeam::getName)
                .collect(Collectors.toList());
        List<String> u19TeamNames = _allTeams.u19Teams.stream()
                .map(U19Team::getName)
                .collect(Collectors.toList());

        System.out.println("Professional Team List by Name\n");
        List<String> TeamsSorted = quickSort(proTeamNames);
        for (String str : TeamsSorted) {
            System.out.println(str);
        }
        System.out.println("\n U19 Team List by Name\n");
        List<String> u19TeamsSorted = quickSort(u19TeamNames);
        for (String str : u19TeamsSorted) {
            System.out.println(str);
        }

    }

    private static List<Staff> CombineStaff(ArrayList<ProfessionalTeam> ProTeams, ArrayList<U19Team> u19Teams) {
        List<Staff> AllStaff = new ArrayList<>();
        for (ProfessionalTeam team : ProTeams) {
            AllStaff.addAll(team.staffList);
        }
        for (U19Team team : u19Teams) {
            AllStaff.addAll(team.staffList);
        }
        return AllStaff;
    }

    public static List<String> getStaffNames(List<Staff> staffList) {
        List<String> staffNames = new ArrayList<>();
        for (Staff staff : staffList) {
            staffNames.add(staff.name);
        }
        return staffNames;
    }

    private static void SortStaff(List<Staff> allStaff) {
        System.out.println("Staff List by Name\n");
        List<String> staffListNames = quickSort(getStaffNames(allStaff));
        for (String str : staffListNames) {
            System.out.println(str);
        }
    }

    /*  After I spent hours to fix it I decided no to go for it. It doesnt work properly.  
    
    private static int binarySearch(List<String> list, String x) {
        int l = 0, r = list.size() - 1;

        // Loop to implement Binary Search 
        while (l <= r) {

            // Calculating mid 
            int m = l + (r - l) / 2;

            int res = x.compareTo(list.get(m));

            // Check if x is present at mid 
            if (res == 0) {
                return m;
            }

            // If x is greater, ignore left half 
            if (res > 0) {
                l = m + 1;
            } // If x is smaller, ignore right half 
            else {
                r = m - 1;
            }
        }
        return -1;
    }
     */
    private static int linearSearch(List<String> list, String ref) {
        int n = list.size();
        int i;
        for (i = 0; i < n; i++) {
            if (list.get(i).equalsIgnoreCase(ref)) {
                return i;
            }
        }
        return -1;
    }

    private static void SearchPlayers(String userInputToSearch, List<ProfessionalPlayer> allProPlayers, List<U19Player> u19Players) {
        int counter = 0;
        int indexOfPlayer = linearSearch(getPlayerNames(allProPlayers), userInputToSearch);
        if (indexOfPlayer != -1) {
            ProfessionalPlayer player = allProPlayers.get(indexOfPlayer);
            System.out.println(player.nationality + " international, " + player.name + " has founded. He played " + player.gamesPlayed + " games in his career.");
            counter++;
        }
        indexOfPlayer = linearSearch(getU19PlayerNames(u19Players), userInputToSearch);
        if (indexOfPlayer != -1) {
            U19Player player = u19Players.get(indexOfPlayer);
            System.out.println(player.nationality + " international, " + player.name + " has founded. He has " + player.Contract + " contract type with his team");
            counter++;
        }
        if (counter == 0) {
            System.out.println("Nobody has founded");
        }
    }

    private static void SearchTeam(String userInputToSearch, AllTeams _allTeams) {
        List<String> proTeamNames = _allTeams.ProTeams.stream()
                .map(ProfessionalTeam::getName)
                .collect(Collectors.toList());
        List<String> u19TeamNames = _allTeams.u19Teams.stream()
                .map(U19Team::getName)
                .collect(Collectors.toList());
        int counter = 0;
        int indexOfTeam = linearSearch(proTeamNames, userInputToSearch);
        if (indexOfTeam != -1) {
            ProfessionalTeam team = _allTeams.ProTeams.get(indexOfTeam);
            System.out.println(team.name + " from " + team.league + " leauge has founded.");
            counter++;
        }
        int indexOfu19Team = linearSearch(u19TeamNames, userInputToSearch);
        if (indexOfu19Team != -1) {
            U19Team u19Team = _allTeams.u19Teams.get(indexOfu19Team);
            System.out.println(u19Team.name + " from " + u19Team.area + " area has founded.");
            counter++;
        }
        if (counter == 0) {
            System.out.println("Nobody has founded");
        }

    }

    private static void SearchStaff(String userInputToSearch, List<Staff> allStaff) {
        int indexOfPlayer = linearSearch(getStaffNames(allStaff), userInputToSearch);
        if (indexOfPlayer != -1) {
            Staff staff = allStaff.get(indexOfPlayer);
            System.out.println(staff.job + " " + staff.name + " has founded.");
        } else {
            System.out.println("Nobody has founded");
        }
    }

    private static void AddPlayer() throws IOException {
        Scanner _scanner = new Scanner(System.in);
        System.out.println("Player`s full name?");
        String name = _scanner.next();
        System.out.println("Player`s Nationality");
        String nationality = _scanner.next();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Age?");
        int age = Integer.parseInt(input.readLine());
        System.out.println("Which team does this player play for?");
        if (age < 19) {
            U19Player player = new U19Player();
            player.name = name;
            player.nationality = nationality;
            int counter = 1;
            for (U19Team team : _allTeams.u19Teams) {
                System.out.println(counter + team.name);
                counter++;
            }
            int teamIndex = Integer.parseInt(input.readLine());
            _allTeams.u19Teams.get(teamIndex).players.add(player);
        } else {
            ProfessionalPlayer player = new ProfessionalPlayer();
            player.name = name;
            player.nationality = nationality;
            int counter = 1;
            for (ProfessionalTeam team : _allTeams.ProTeams) {
                System.out.println(counter + team.name);
                counter++;
            }
            int teamIndex = Integer.parseInt(input.readLine());
            _allTeams.ProTeams.get(teamIndex).players.add(player);
        }
        System.out.println(name + " Added Successfully!");
    }

    private static void ListAll(AllTeams _allTeams) {
        for (ProfessionalTeam team : _allTeams.ProTeams) {
            System.out.println("\n#######    " + team.name + "     #######");
            for (ProfessionalPlayer player : team.players) {
                System.out.println(player.jerseyNumber + " " + player.name + " " + player.position);
            }
            for (Staff staff : team.staffList) {
                System.out.println(staff.name + " " + staff.job);
            }
        }
        for (U19Team team : _allTeams.u19Teams) {
            System.out.println("\n#######    " + team.name + "     #######");
            for (U19Player player : team.players) {
                System.out.println(player.jerseyNumber + " " + player.name + " " + player.position);
            }
            for (Staff staff : team.staffList) {
                System.out.println(staff.name + " " + staff.job);
            }
        }
    }

    // Function to get a random name from the list
    private static String getRandomName() {
        Random random = new Random();
        int index = random.nextInt(randomNames.size());
        return randomNames.get(index);
    }

    private static void GenerateRandomPlayer() throws IOException {
        String name = getRandomName();
        String nationality = "Ireland";
        Random r = new Random();
        int low = 14;
        int high = 40;
        int age = r.nextInt(high - low) + low;
        low = 1;
        high = 99;
        int kitNumber = r.nextInt(high - low) + low;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Which team does this player play for?");
        if (age < 19) {
            U19Player player = new U19Player();
            player.name = name;
            player.nationality = nationality;
            player.jerseyNumber = kitNumber;
            int counter = 1;
            for (U19Team team : _allTeams.u19Teams) {
                System.out.println(counter + team.name);
                counter++;
            }
            int teamIndex = Integer.parseInt(input.readLine());
            _allTeams.u19Teams.get(teamIndex - 1).players.add(player);
        } else {
            ProfessionalPlayer player = new ProfessionalPlayer();
            player.name = name;
            player.nationality = nationality;
            player.jerseyNumber = kitNumber;
            int counter = 1;
            for (ProfessionalTeam team : _allTeams.ProTeams) {
                System.out.println(counter + team.name);
                counter++;
            }
            int teamIndex = Integer.parseInt(input.readLine());
            _allTeams.ProTeams.get(teamIndex - 1).players.add(player);
        }
        System.out.println(name + " Added Successfully!");
    }

    private static ArrayList<String> fillRandomNames() {
        ArrayList<String> randomList = new ArrayList<>();
        randomList.add("Kamil Lewandowkski");
        randomList.add("Hidayet Turkoglu");
        randomList.add("Bukoyoka Grealish");
        randomList.add("Erdim Terzic");
        randomList.add("Marco Wirtz");
        randomList.add("Sebastian Reus");
        randomList.add("Micheal Scottish");
        randomList.add("Kevin Malone");
        randomList.add("Arda Gulen");
        randomList.add("Allejandro Domuininguez");
        randomList.add("Federico Chiesa");
        randomList.add("Andre Batistuta");
        randomList.add("Leo Spike Spiegel");
        randomList.add("Kai Draxler");
        randomList.add("Alex Alves");
        randomList.add("Marcelo Coutinho");
        randomList.add("Roberto Ronaldo");
        randomList.add("Carlos Souza");
        randomList.add("Dimitris Papa");
        randomList.add("Patrick Hjarnic");
        randomList.add("Bojan Modric");
        randomList.add("Alberto Barella");
        randomList.add("Sven Erikson");
        randomList.add("Peterri Koponnen");
        randomList.add("Erling Odegaard");
        return randomList;
    }
}
