/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import enums.Measurement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 *
 * @author pc
 */
public class WorkoutItem extends DefaultDomainObject{
    private WorkoutRecord workoutRecord;
    private int itemSN; //sequence number
    private Measurement intensity;
    private int numOfSeries;
    private double mass;
    private String comment;
    private Activity activity;

    public WorkoutItem(){
    }

    public WorkoutItem(WorkoutRecord workoutRecord, int itemSN, Measurement intensity, int numOfSeries, double mass, String comment, Activity activity) {
        this.workoutRecord = workoutRecord;
        this.itemSN = itemSN;
        this.intensity = intensity;
        this.numOfSeries = numOfSeries;
        this.mass = mass;
        this.comment = comment;
        this.activity = activity;
    }

    public WorkoutRecord getWorkoutRecord() {
        return workoutRecord;
    }

    public void setWorkoutRecord(WorkoutRecord workoutRecord) {
        this.workoutRecord = workoutRecord;
    }

    public int getItemSN() {
        return itemSN;
    }

    public void setItemSN(int itemSN) {
        this.itemSN = itemSN;
    }

    public Measurement getIntensity() {
        return intensity;
    }

    public void setIntensity(Measurement intensity) {
        this.intensity = intensity;
    }

    public int getNumOfSeries() {
        return numOfSeries;
    }

    public void setNumOfSeries(int numOfSeries) {
        this.numOfSeries = numOfSeries;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String returnAttrValues() {
        return workoutRecord.getIdWorkoutRecord()+","+itemSN+",'"+intensity.getSerbianName()+"',"+numOfSeries+","+mass+",'"+comment+"',"+activity.getIdActivity();
    }

    @Override
    public String returnClassName() {
        return "workoutitem";
    }

    @Override
    public String setAttrValues() {
        return "intensity='"+intensity.getSerbianName()+"',numOfSeries="+numOfSeries+",mass="+mass+",comment='"+comment+"',idActivity="+activity.getIdActivity();
    }

    @Override
    public String returnInsertColumns() {
        return "(itemSN, intensity, numOfSeries, mass, comment, idActivity)";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            itemSN=rs.getInt("itemSN");
            intensity=Measurement.fromSerbianName(rs.getString("intensity"));
            numOfSeries=rs.getInt("numOfSeries");
            mass=rs.getDouble("mass");
            comment=rs.getString("comment");
            //record i activity dodati u kontroleru

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
