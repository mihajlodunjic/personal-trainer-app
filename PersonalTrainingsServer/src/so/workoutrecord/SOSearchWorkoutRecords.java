/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.workoutrecord;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Trainer;
import domain.WorkoutRecord;
import dto.WorkoutRecordCriteria;
import java.sql.Date;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOSearchWorkoutRecords extends AbstractSO {

    private LinkedList<WorkoutRecord> list;

    public LinkedList<WorkoutRecord> getList() {
        return list;
    }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof WorkoutRecordCriteria)) {
            throw new Exception("Prosleđen objekat nije WorkoutRecordCriteria");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        WorkoutRecordCriteria c = (WorkoutRecordCriteria) ddo;

        WorkoutRecord probe = new WorkoutRecord();
        StringBuilder cond = new StringBuilder("1");
        StringBuilder join = new StringBuilder();

        // Aktivnost → traži kroz JOIN na workoutitem
        if (c.getActivityId() != null && c.getActivityId() > 0) {
            join.append(" JOIN workoutitem wi ON wi.idWorkoutRecord = wr.idWorkoutRecord ");
            cond.append(" AND wi.idActivity = ").append(c.getActivityId());
        }
        // Trener
        if (c.getTrainerId() != null && c.getTrainerId() > 0) {
            cond.append(" AND wr.idTrainer = ").append(c.getTrainerId());
        }
        // Klijent
        if (c.getClientId() != null && c.getClientId() > 0) {
            cond.append(" AND wr.idClient = ").append(c.getClientId());
        }
        // Intenzitet
        if (c.getAvgIntensity() != null) {
            cond.append(" AND wr.avgIntensity = '")
                    .append(c.getAvgIntensity().getSerbianName()).append("'");
        }
        // Datum od/do
        if (c.getDateFrom() != null) {
            cond.append(" AND wr.workoutDate >= '").append(Date.valueOf(c.getDateFrom())).append("'");
        }
        if (c.getDateTo() != null) {
            cond.append(" AND wr.workoutDate <= '").append(Date.valueOf(c.getDateTo())).append("'");
        }

        probe.setSearchCondition(cond.toString());
        probe.setJoinClause(join.toString());

        list = (LinkedList<WorkoutRecord>) (LinkedList<?>) DatabaseBroker.select(probe);
        for (WorkoutRecord wr : list){
            Trainer trainer=new Trainer();
            trainer.setIdTrаiner(wr.getTrainer().getIdTrаiner());
            trainer.setSearchCondition("idTrainer="+trainer.getIdTrаiner());
            DatabaseBroker.findRowAndReturn(trainer);
            wr.setTrainer(trainer);
        }
    }

}
