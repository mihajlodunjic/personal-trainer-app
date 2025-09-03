package dto;

import enums.Measurement;
import java.io.Serializable;
import java.time.LocalDate;

public class WorkoutRecordCriteria implements Serializable {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer trainerId;   // optional
    private Integer clientId;    // optional
    private Integer activityId;  // optional (filter kroz JOIN na workoutitem)
    private Measurement avgIntensity; // optional

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
}
