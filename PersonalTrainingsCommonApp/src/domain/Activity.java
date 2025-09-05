/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import enums.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Activity extends DefaultDomainObject {

    private int idActivity;
    private Category category;
    private String name;

    public Activity(int idActivity, Category category, String name) {
        this.idActivity = idActivity;
        this.category = category;
        this.name = name;
    }

    public Activity() {
    }

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

    @Override
    public String returnAttrValues() {
        return "'" + category.getSerbianName() + "','" + name + "'";
    }

    @Override
    public String returnClassName() {
        return "activity";
    }

    @Override
    public String setAttrValues() {
        return "category= '" + category.getSerbianName() + "', name='" + name + "'";
    }

    @Override
    public String returnInsertColumns() {
        return "(category, name)";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            int idActivity = rs.getInt("idActivity");
            Category category = Category.fromSerbianName(rs.getString("category"));
            String name = rs.getString("name");
            this.idActivity = idActivity;
            this.category = category;
            this.name = name;
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public String alias() {
        return " a ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public LinkedList<DefaultDomainObject> returnList(ResultSet rs) throws Exception {
        LinkedList<DefaultDomainObject> list = new LinkedList<>();
        while (rs.next()) {
            Activity a = new Activity();
            a.setAttributes(rs); // popunjava idActivity, category, name
            list.add(a);
        }
        rs.close();
        return list;
    }

}
