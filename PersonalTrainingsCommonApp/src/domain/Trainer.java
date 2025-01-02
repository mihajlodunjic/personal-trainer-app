/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Trainer extends DefaultDomainObject{
    private int idTrainer;
    private String userName;
    private String password;
    private String name;
    private String lastName;

    public Trainer(int idTrainer, String userName, String password, String name, String lastName) {
        this.idTrainer = idTrainer;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.searchCondition="userName = '" + userName + "' AND password = '" + password + "'";
    }
    public Trainer(){}

    public int getIdTrаiner() {
        return idTrainer;
    }

    public void setIdTrаiner(int idTrаiner) {
        this.idTrainer = idTrаiner;
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

    public void setSearchCondition(String searchCondition){
        this.searchCondition=searchCondition;
    }
    
    
    @Override
    public String returnAttrValues() {
        return "'"+userName+"','"+password+"','"+name+"','"+lastName+"'";
    }

    @Override
    public String returnClassName() {
        return "trainer";
    }

    @Override
    public String returnSearchCondition() {
        return searchCondition;
    }

    @Override
    public String setAttrValues() {
        return "username="+"'"+userName+"',password='"+"'"+password+", name="+"'"+name+",lastname="+"'"+lastName;
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            int id=rs.getInt("idTrainer");
            String userName=rs.getString("userName");
            String password=rs.getString("password");
            String name=rs.getString("t_name");
            String lastName=rs.getString("t_lastName");
            System.out.println("U domenu: "+id+" "+userName+" "+password+" "+name+" "+lastName);
            this.idTrainer=id;
            this.userName=userName;
            this.password=password;
            this.name=name;
            this.lastName=lastName;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trainer{" + "idTrainer=" + idTrainer + ", userName=" + userName + ", password=" + password + ", name=" + name + ", lastName=" + lastName + '}';
    }

    @Override
    public String returnInsertColumns() {
        return "(userName, password, t_name, t_lastName)";
    }

    @Override
    public String alias() {
        return " t ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public LinkedList<DefaultDomainObject> returnList(ResultSet rs) throws Exception{
        LinkedList<DefaultDomainObject> list=new LinkedList<>();
        while (rs.next()){
            Trainer t=new Trainer();
            t.setIdTrаiner(rs.getInt("idTrainer"));
            t.setUserName(rs.getString("userName"));
            t.setName(rs.getString("t_name"));
            t.setLastName(rs.getString("t_lastname"));
            t.setPassword(rs.getString("password"));
            list.add(t);
        }    
        return list;
    }
    
}
