/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import interfaces.DefaultDomainObject;
import java.sql.ResultSet;

/**
 *
 * @author pc
 */
public class Trainer implements DefaultDomainObject{
    private int idTrаiner;
    private String userName;
    private String password;
    private String name;
    private String lastName;

    public Trainer(int idTrener, String korisnickoIme, String lozinka, String ime, String prezime) {
        this.idTrаiner = idTrener;
        this.userName = korisnickoIme;
        this.password = lozinka;
        this.name = ime;
        this.lastName = prezime;
    }
    public Trainer(){}

    public int getIdTrаiner() {
        return idTrаiner;
    }

    public void setIdTrаiner(int idTrаiner) {
        this.idTrаiner = idTrаiner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String returnAttrValues() {
        //to do
        return null;
    }

    @Override
    public String returnClassName() {
        //to do
        return null;
    }

    @Override
    public String returnSearchCondition() {
        //to do
        return null;
    }

    @Override
    public String setAttrValues() {
        //to do
        return null;
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        //to do
        return true;
    }
    
}
