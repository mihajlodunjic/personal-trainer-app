/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enums.Category;

/**
 *
 * @author pc
 */
public class Activity {
    private int idActivity;
    private Category category;
    private String name;

    public Activity(int idActivity, Category category, String name) {
        this.idActivity = idActivity;
        this.category = category;
        this.name = name;
    }

    public Activity(){}
    
    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
