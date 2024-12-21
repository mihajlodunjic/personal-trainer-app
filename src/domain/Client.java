/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import enums.Gender;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author pc
 */
public class Client extends DefaultDomainObject{
    private int idClient;
    private String name;
    private String lastName;
    private Gender gender;
    private LocalDate birthday;
    private String mobilePhone;
    private Gym gym;

    public Client(int idClient, String name, String lastName, Gender gender, LocalDate birthday, String mobilePhone, Gym gym) {
        this.idClient = idClient;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.mobilePhone = mobilePhone;
        this.gym = gym;
    }

    public Client() {
    }

    
    
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    @Override
    public String returnAttrValues() {
        Date date=Date.valueOf(birthday);
        return "'"+name+"','"+lastName+"','"+gender.getSerbianName()+"','"+date.toString()+"','"+mobilePhone+"',"+gym.getIdGym();
    }

    @Override
    public String returnClassName() {
        return "client";
    }


    @Override
    public String setAttrValues() {
        Date date=Date.valueOf(birthday);
        return "name='"+name+",lastName='"+lastName+"',gender='"+gender.getSerbianName()+"',birthday='"+date.toString()+"',mobilePhone='"+mobilePhone+"',idGym="+gym.getIdGym();
    }

    @Override
    public String returnInsertColumns() {
        return "(name,lastName,gender,birthday,mobilePhone,idGym)";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            String name=rs.getString(2);
            String lastName=rs.getString(3);
            Gender gender=Gender.fromSerbianName(rs.getString(4));
            Date sqlDate=rs.getDate(5);
            LocalDate birthday=sqlDate.toLocalDate();
            String mobilePhone=rs.getString(6);
            //to do
            //Gym
            return true;
        } catch (Exception e) {
            return false;
        }
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
