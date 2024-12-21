/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author pc
 */
public enum Gender {
    MALE("musko"), FEMALE("zensko");
    private String serbianName;
    private Gender (String serbianName){
        this.serbianName=serbianName;
        
    }
    public String getSerbianName(){
        return serbianName;
    }
    public static Gender fromSerbianName(String serbianName) {
        for (Gender gender : Gender.values()) {
            if (gender.getSerbianName().equalsIgnoreCase(serbianName)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("No enum constant for Serbian name: " + serbianName);
    }
}
