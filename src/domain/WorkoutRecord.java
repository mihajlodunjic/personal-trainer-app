/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enums.Measurement;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author pc
 */
public class WorkoutRecord {
    private int workoutRecord;
    private LocalDate workoutDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Measurement avgIntensity;
    private Trainer trainer;
    private Client client;

    public WorkoutRecord() {
    }

    public WorkoutRecord(int workoutRecord, LocalDate workoutDate, LocalTime startTime, LocalTime endTime, Measurement avgIntensity, Trainer trainer, Client client) {
        this.workoutRecord = workoutRecord;
        this.workoutDate = workoutDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.avgIntensity = avgIntensity;
        this.trainer = trainer;
        this.client = client;
    }

    public int getWorkoutRecord() {
        return workoutRecord;
    }

    public void setWorkoutRecord(int workoutRecord) {
        this.workoutRecord = workoutRecord;
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
}
