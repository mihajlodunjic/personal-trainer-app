/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import enums.Measurement;
import java.sql.ResultSet;

/**
 *
 * @author pc
 */
public class Gym extends DefaultDomainObject{
    private int idGym;
    private String name;
    private String Address;
    private Measurement equipmentLevel;
    private String mobilePhone;

    public Gym() {
    }

    public Gym(int idGym, String name, String Address, Measurement equipmentLevel, String mobilePhone) {
        this.idGym = idGym;
        this.name = name;
        this.Address = Address;
        this.equipmentLevel = equipmentLevel;
        this.mobilePhone = mobilePhone;
    }
    
    

    public int getIdGym() {
        return idGym;
    }

    public void setIdGym(int idGym) {
        this.idGym = idGym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Measurement getEquipmentLevel() {
        return equipmentLevel;
    }

    public void setEquipmentLevel(Measurement equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String returnAttrValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String returnClassName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setAttrValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String returnInsertColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNumberOfRelatedObjects() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DefaultDomainObject getRelatedObject(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean populateRelatedObject(ResultSet rs, int rowIndex, int relatedObjectIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
