/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2023226;

/**
 *
 * @author kaany
 */
public enum ContractEnum {
    trial,
    young,
    promising;
        //I couldnt find a basic way to bring enum values by giving a number. So I wrote a method for every enum
    public static ContractEnum ContractTypeGetter(int select) {  
        switch (select) {
            case 1:
                return trial;
            case 2:
                return young;
            case 3:
                return promising;
        }
           return young;
    }
}
