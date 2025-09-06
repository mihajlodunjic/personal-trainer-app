/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.workoutrecord;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Client;
import domain.Trainer;
import domain.WorkoutItem;
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

        //ako je zadat id trazimo samo po njemu
        if (c.getWorkoutRecordId() != null && c.getWorkoutRecordId() > 0) {
            cond = new StringBuilder("wr.idWorkoutRecord = " + c.getWorkoutRecordId());
        } else {
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
            // Datum
            if (c.getDateFrom() != null) {
                cond.append(" AND wr.workoutDate >= '").append(Date.valueOf(c.getDateFrom())).append("'");
            }
            if (c.getDateTo() != null) {
                cond.append(" AND wr.workoutDate <= '").append(Date.valueOf(c.getDateTo())).append("'");
            }
        }

        probe.setSearchCondition(cond.toString());
        probe.setJoinClause(join.toString());

        list = (LinkedList<WorkoutRecord>) (LinkedList<?>) DatabaseBroker.select(probe);

        for (WorkoutRecord wr : list) {
            Trainer trainer = new Trainer();
            trainer.setIdTrаiner(wr.getTrainer().getIdTrаiner());
            trainer.setSearchCondition("idTrainer=" + trainer.getIdTrаiner());
            DatabaseBroker.findRowAndReturn(trainer);
            wr.setTrainer(trainer);

            Client client = new Client();
            client.setIdClient(wr.getClient().getIdClient());
            client.setSearchCondition("idClient=" + client.getIdClient());
            DatabaseBroker.findRowAndReturn(client);
            wr.setClient(client);

            WorkoutItem wi = new WorkoutItem();
            wi.setSearchCondition("wi.idWorkoutRecord=" + wr.getIdWorkoutRecord());
            LinkedList<WorkoutItem> itemList = (LinkedList<WorkoutItem>) (LinkedList<?>) DatabaseBroker.select(wi);
            for (WorkoutItem it : itemList) {
                System.out.println(it.getActivity().getName());
                it.setWorkoutRecord(wr);
            }
            wr.setItems(itemList);

        }
    }

}
