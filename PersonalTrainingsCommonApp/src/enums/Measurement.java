/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author pc
 */
public enum Measurement {
    LOW("nizak"), MODERATE("srednji"), HIGH("visok");
    private String serbianName;
    
    private Measurement(String serbianName){
        this.serbianName=serbianName;
    }
    public String getSerbianName(){
        return serbianName;
    }
    public static Measurement fromSerbianName(String serbianName) {
        for (Measurement measurement : Measurement.values()) {
            if (measurement.getSerbianName().equalsIgnoreCase(serbianName)) {
                return measurement;
            }
        }
        throw new IllegalArgumentException("No enum constant for Serbian name: " + serbianName);
    }
}
