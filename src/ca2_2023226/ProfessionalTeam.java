/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2023226;

import java.util.ArrayList;

/**
 *
 * @author kaany
 */
public class ProfessionalTeam extends Team {

    public String league;
    public ArrayList<Staff> staffList;
    public ArrayList<ProfessionalPlayer> players;

    public ProfessionalTeam() {
        players = new ArrayList<ProfessionalPlayer>();
        staffList = new ArrayList<Staff>();
    }
}