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
import java.util.ArrayList;

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
        searchCondition="idClient="+this.idClient;
        searchConditionForRelatedObjects="idGym="+gym.getIdGym();
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
            String name=rs.getString("c_name");
            String lastName=rs.getString("c_lastName");
            Gender gender=Gender.fromSerbianName(rs.getString("gender"));
            Date sqlDate=rs.getDate("birthday");
            LocalDate birthday=sqlDate.toLocalDate();
            String mobilePhone=rs.getString("c_mobilePhone");
            //to do
            //Gym
            // u kontroleru staviti gym mozda??
            int gymId = rs.getInt("idGym");
            this.name=name;
            this.lastName=lastName;
            this.gender=gender;
            this.birthday=birthday;
            this.mobilePhone=mobilePhone;
            gym.setIdGym(gymId);

        return true;

        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public String alias() {
        return " c ";
    }

    @Override
    public String join() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DefaultDomainObject> returnList(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
