/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author pc
 */
public enum Category {
    BACK("ledja"),
    CHEST("grudi"), 
    SHOULDERS("ramena"),
    BICEPS("biceps"), 
    TRICEPS("triceps"), 
    CORE("stomak"), 
    QUADRICEPS("kvadriceps"), 
    HAMSTRINGS("zadnjaloza"),
    GLUTEUS("gluteus"),
    CALVES("list");
    
    private String serbianName;
    
    private Category (String serbianName){
        this.serbianName=serbianName;
    }
    
    public String getSerbianName(){
        return serbianName;
    }
    
     public static Category fromSerbianName(String serbianName) {
        for (Category category : Category.values()) {
            if (category.getSerbianName().equalsIgnoreCase(serbianName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No enum constant for Serbian name: " + serbianName);
    }
}
