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
public class WorkoutRecord extends DefaultDomainObject {

    private int idWorkoutRecord;
    private LocalDate workoutDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime duration;
    private Measurement avgIntensity;
    private Trainer trainer;
    private Client client;
    private LinkedList<WorkoutItem> items;
    private String joinClause = "";

    public LinkedList<WorkoutItem> getItems() {
        return items;
    }

    public void setItems(LinkedList<WorkoutItem> items) {
        this.items = items;
        calculateAvgIntensity();
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
        calculateDuration();
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

    public LocalTime getDuration() {
        calculateDuration();
        return duration;
    }

    public void setJoinClause(String joinClause) {
        this.joinClause = joinClause;
    }

    @Override
    public String returnAttrValues() {
        Date date = Date.valueOf(workoutDate);
        Time start = Time.valueOf(startTime);
        Time end = Time.valueOf(endTime);
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.toSeconds() % 60;
        String durationString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        //nije gotovo
        return "'" + date + "','" + start + "','" + end + "','" + durationString + "','" + avgIntensity.getSerbianName() + "'," + trainer.getIdTrаiner() + "," + client.getIdClient();
    }

    @Override
    public String returnClassName() {
        return "workoutrecord";
    }

    @Override
    public String setAttrValues() {
        Date date = Date.valueOf(workoutDate);
        Time start = Time.valueOf(startTime);
        Time end = Time.valueOf(endTime);
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.toSeconds() % 60;
        String durationString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return "workoutDate='" + date + "',startTime='" + start + "',endTime='" + end + "',duration='" + durationString + "',avgintensity='" + avgIntensity.getSerbianName() + "',idTrainer=" + trainer.getIdTrаiner() + ",idClient=" + client.getIdClient();
    }

    @Override
    public String returnInsertColumns() {
        return "(workoutDate,startTime,endTime,duration,avgIntensity,idTrainer,idClient)";
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            this.idWorkoutRecord = rs.getInt("idWorkoutRecord");
            this.workoutDate = rs.getDate("workoutDate").toLocalDate();
            this.startTime = rs.getTime("startTime").toLocalTime();
            this.endTime = rs.getTime("endTime").toLocalTime();
            this.avgIntensity = Measurement.fromSerbianName(rs.getString("avgIntensity"));
            int idTrainer = rs.getInt("idTrainer");
            int idClient = rs.getInt("idClient");

            if (this.trainer == null) {
                this.trainer = new Trainer();
            }
            if (this.client == null) {
                this.client = new Client();
            }
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
        return " wr ";
    }

    @Override
    public String join() {
        return joinClause == null ? "" : joinClause;
    }

    @Override
    public LinkedList<DefaultDomainObject> returnList(ResultSet rs) throws Exception {
        LinkedList<DefaultDomainObject> list = new LinkedList<>();
        while (rs.next()) {
            WorkoutRecord wr = new WorkoutRecord();
            wr.setAttributes(rs);
            list.add(wr);
        }
        rs.close();
        return list;
    }

    public void calculateDuration() {
        Duration diff = Duration.between(startTime, endTime);

        long hours = diff.toHours();
        long minutes = diff.toMinutes() % 60;

        duration = LocalTime.of((int) hours, (int) minutes);
    }

    public void setWorkoutRecordForItems() {
        for (WorkoutItem wi : items) {
            wi.setWorkoutRecord(this);
        }
    }

    public void calculateAvgIntensity() {
        double sum = 0;
        for (WorkoutItem wi : items) {
            sum += wi.getIntensity().ordinal();
        }

        double avg = sum / items.size();
        int rounded = (int) Math.round(avg);
        avgIntensity = Measurement.values()[rounded];

    }

    @Override
    public String toString() {
        return "WorkoutRecord{" + "idWorkoutRecord=" + idWorkoutRecord + ", workoutDate=" + workoutDate + ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration + ", avgIntensity=" + avgIntensity + ", trainer=" + trainer + ", client=" + client + ", items=" + items + '}';
    }

}
