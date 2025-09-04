package dto;

import abstractClass.DefaultDomainObject;
import enums.Measurement;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

public class WorkoutRecordCriteria extends DefaultDomainObject {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer trainerId;  
    private Integer clientId;   
    private Integer activityId;  
    private Measurement avgIntensity; 

    public LocalDate getDateFrom() { return dateFrom; }
    public void setDateFrom(LocalDate dateFrom) { this.dateFrom = dateFrom; }

    public LocalDate getDateTo() { return dateTo; }
    public void setDateTo(LocalDate dateTo) { this.dateTo = dateTo; }

    public Integer getTrainerId() { return trainerId; }
    public void setTrainerId(Integer trainerId) { this.trainerId = trainerId; }

    public Integer getClientId() { return clientId; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }

    public Integer getActivityId() { return activityId; }
    public void setActivityId(Integer activityId) { this.activityId = activityId; }

    public Measurement getAvgIntensity() { return avgIntensity; }
    public void setAvgIntensity(Measurement avgIntensity) { this.avgIntensity = avgIntensity; }

    @Override public String alias() { return ""; }
    @Override public String join() { return ""; }
    @Override public String returnAttrValues() { return ""; }
    @Override public String returnClassName() { return ""; }
    @Override public String setAttrValues() { return ""; }
    @Override public String returnInsertColumns() { return ""; }
    @Override public boolean setAttributes(ResultSet rs) { return false; }
    @Override public LinkedList<DefaultDomainObject> returnList(ResultSet rs) { return new LinkedList<>(); }
}
