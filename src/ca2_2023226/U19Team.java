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

public class U19Team extends Team {
    public ConditionEnum facilities;
    public ArrayList<U19Player> players; 
    public ArrayList<Staff> staffList;
    public U19Team() {
    players = new ArrayList<>();
    staffList = new ArrayList<>();
    }
}
