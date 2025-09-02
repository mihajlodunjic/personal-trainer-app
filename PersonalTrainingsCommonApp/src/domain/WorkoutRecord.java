/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import abstractClass.DefaultDomainObject;
import enums.Measurement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author pc
 */
public class WorkoutRecord extends DefaultDomainObject{
    private int idWorkoutRecord;
    private LocalDate workoutDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Measurement avgIntensity;
    private Trainer trainer;
    private Client client;
    private List<WorkoutItem> items;

    public List<WorkoutItem> getItems() {
        return items;
    }

    public void setItems(List<WorkoutItem> items) {
        this.items = items;
    }

    public WorkoutRecord() {
    }

    public WorkoutRecord(int workoutRecord, LocalDate workoutDate, LocalTime startTime, LocalTime endTime, Measurement avgIntensity, Trainer trainer, Client client) {
        this.idWorkoutRecord = workoutRecord;
        this.workoutDate = workoutDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.avgIntensity = avgIntensity;
        this.trainer = trainer;
        this.client = client;
    }

    public int getIdWorkoutRecord() {
        return idWorkoutRecord;
    }

    public void setIdWorkoutRecord(int idWorkoutRecord) {
        this.idWorkoutRecord = idWorkoutRecord;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Measurement getAvgIntensity() {
        return avgIntensity;
    }

    public void setAvgIntensity(Measurement avgIntensity) {
        this.avgIntensity = avgIntensity;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public LocalTime getDuration(){
        //to do
        return null;
    }

    @Override
    public String returnAttrValues() {
        Date date=Date.valueOf(workoutDate);
        Time start=Time.valueOf(startTime);
        Time end=Time.valueOf(endTime);
        Duration duration=Duration.between(startTime, endTime);
        long hours=duration.toHours();
        long minutes=duration.toMinutes()%60;
        long seconds=duration.toSeconds()%60;
        String durationString=String.format("%02d:%02d:%02d", hours,minutes,seconds);
        //nije gotovo
        return "'"+date+"','"+start+"','"+end+"','"+durationString+"','"+avgIntensity.getSerbianName()+"',"+trainer.getIdTrаiner()+","+client.getIdClient();
    }

    @Override
    public String returnClassName() {
        return "workoutrecord";
    }

    @Override
    public String setAttrValues() {
        Date date=Date.valueOf(workoutDate);
        Time start=Time.valueOf(startTime);
        Time end=Time.valueOf(endTime);
        Duration duration=Duration.between(startTime, endTime);
        long hours=duration.toHours();
        long minutes=duration.toMinutes()%60;
        long seconds=duration.toSeconds()%60;
        String durationString=String.format("%02d:%02d:%02d", hours,minutes,seconds);
        return "workoutDate='"+date+"',startTime='"+start+"',endTime='"+end+"',duration='"+durationString+"',avgintensity='"+avgIntensity.getSerbianName()+"',idTrainer="+trainer.getIdTrаiner()+",idClient="+client.getIdClient();
    }

    @Override
    public String returnInsertColumns() {
        return "(workoutDate,startTime,endTime,duration,avgIntensity,idTrainer,idClient)";
    }
    
    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            LocalDate workoutDate=rs.getDate("workoutDate").toLocalDate();
            LocalTime startTime=rs.getTime("startTime").toLocalTime();
            LocalTime endTime=rs.getTime("endTime").toLocalTime();
            LocalTime duration=rs.getTime("duration").toLocalTime();
            Measurement avgIntensity=Measurement.fromSerbianName(rs.getString("avgIntensity"));
            int idTrainer=rs.getInt("idTrainer");
            int idClient=rs.getInt("idClient");
            this.workoutDate=workoutDate;
            this.startTime=startTime;
            this.endTime=endTime;
            this.avgIntensity=avgIntensity;
            if (this.trainer == null) this.trainer = new Trainer();
            if (this.client  == null) this.client  = new Client();
            trainer.setIdTrаiner(idTrainer);
            client.setIdClient(idClient);
            
            
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
