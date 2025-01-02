/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Sertificate extends DefaultDomainObject{
    private int idSertificate;
    private String name;
    private String publisher;

    public Sertificate() {
    }

    public Sertificate(int idSertificate, String name, String publisher) {
        this.idSertificate = idSertificate;
        this.name = name;
        this.publisher = publisher;
    }

    public int getIdSertificate() {
        return idSertificate;
    }

    public void setIdSertificate(int idSertificate) {
        this.idSertificate = idSertificate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String returnAttrValues() {
        return "'"+name+"','"+publisher+"'";
    }

    @Override
    public String returnClassName() {
        return "sertificate";
    }

    @Override
    public String setAttrValues() {
        return "name='"+name+"',publisher='"+publisher+"'";
    }

    @Override
    public String returnInsertColumns() {
        return "(name,publisher)";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            idSertificate=rs.getInt("idSertificate");
            name=rs.getString("s_name");
            publisher=rs.getString("publisher");
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String alias() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String join() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LinkedList<DefaultDomainObject> returnList(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
