/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2023226;

/**
 *
 * @author kaany
 */
public enum JobEnum {
    Head_Coach,
    Assistan_Coach,
    Trainer,
    Physio,
    Analist,
    Scout;
    //I couldnt find a basic way to bring enum values by giving a number. So I wrote a method for every enum
    public static JobEnum Jobgetter(int select) {
        switch (select) {
            case 1:
                return Head_Coach;
            case 2:
                return Assistan_Coach;
            case 3:
                return Trainer;
            case 4:
                return Physio;
            case 5:
                return Analist;
            case 6:
                return Scout;
            default:
                break;
        }
           return Trainer;
    }
}
