/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2023226;

/**
 *
 * @author kaany
 */
public enum PositionEnum {
    Loose_head_prop,
    Hooker,
    Tight_head_prop,
    Second_row,
    Blind_side_flanker,
    Open_side_flanker,
    Number_eight,
    Scrum_half,
    Fly_half,
    Left_wing,
    Inside_center,
    Outside_center,
    Right_wing,
    Full_back;
    //I couldnt find a basic way to bring enum values by giving a number. So I wrote a method for every enum

    public static PositionEnum PositionGetter(int select) {
        switch (select) {
            case 1:
                return Loose_head_prop;
            case 2:
                return Hooker;
            case 3:
                return Tight_head_prop;
            case 4:
                return Second_row;
            case 5:
                return Blind_side_flanker;
            case 6:
                return Open_side_flanker;
            case 7:
                return Number_eight;
            case 8:
                return Scrum_half;
            case 9:
                return Fly_half;
            case 10:
                return Left_wing;
            case 11:
                return Inside_center;
            case 12:
                return Outside_center;
            case 13:
                return Right_wing;
            case 14:
                return Full_back;
            default:
                break;
        }
        return Full_back;
    }
}
