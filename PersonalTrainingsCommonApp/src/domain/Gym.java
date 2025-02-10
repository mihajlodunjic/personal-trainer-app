/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import enums.Measurement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Gym extends DefaultDomainObject{
    private int idGym;
    private String name;
    private String address;
    private Measurement equipmentLevel;
    private String mobilePhone;

    public Gym() {
    }

    public Gym(int idGym, String name, String address, Measurement equipmentLevel, String mobilePhone) {
        this.idGym = idGym;
        this.name = name;
        this.address = address;
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
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "'"+name+"','"+address+"','"+equipmentLevel.getSerbianName()+"','"+mobilePhone+"'";
    }

    @Override
    public String returnClassName() {
        return "gym";
    }

    @Override
    public String setAttrValues() {
        return "name='"+name+"',address='"+address+"',equipmentLeve='"+equipmentLevel.getSerbianName()+"',mobilePhone='"+mobilePhone+"'";
    }

    @Override
    public String returnInsertColumns() {
        return "(g_name,address,equipmentLevel,g_mobilePhone)";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            int idGym=rs.getInt("idGym");
            String name=rs.getString("g_name");
            String address=rs.getString("address");
            Measurement equipmentLevel=Measurement.fromSerbianName(rs.getString("equipmentLevel"));
            String mobilePhone=rs.getString("g_mobilePhone");
            this.idGym=idGym;
            this.name=name;
            this.address=address;
            this.equipmentLevel=equipmentLevel;
            this.mobilePhone=mobilePhone;
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String alias() {
        return " g ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public LinkedList<DefaultDomainObject> returnList(ResultSet rs) throws Exception{
        LinkedList<DefaultDomainObject> list=new LinkedList<>();
        while(rs.next()){
            Gym gym=new Gym();
//            gym.setIdGym(rs.getInt("idGym"));
//            gym.setName(rs.getString("g_name"));
//            gym.setAddress(rs.getString("address"));
//            gym.setEquipmentLevel(Measurement.fromSerbianName(rs.getString("equipmentLevel")));
//            gym.setMobilePhone(rs.getString("g_mobilePhone"));
            gym.setAttributes(rs);
            list.add(gym);
        }
        return list;
    }
}
