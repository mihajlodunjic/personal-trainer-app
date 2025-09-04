package so.workoutitem;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.WorkoutItem;
import domain.WorkoutRecord;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOGetWorkoutItemsByRecord extends AbstractSO {

    private LinkedList<WorkoutItem> list;

    public LinkedList<WorkoutItem> getList() {
        return list;
    }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof WorkoutRecord)) {
            throw new Exception("Očekivan je WorkoutRecord");
        }
        if (((WorkoutRecord) ddo).getIdWorkoutRecord() <= 0) {
            throw new Exception("Nedostaje idWorkoutRecord");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        WorkoutRecord wr = (WorkoutRecord) ddo;
        WorkoutItem probe = new WorkoutItem();
        probe.setSearchCondition("wi.idWorkoutRecord=" + wr.getIdWorkoutRecord());
        list = (LinkedList<WorkoutItem>) (LinkedList<?>) DatabaseBroker.select(probe);
        for (WorkoutItem it : list) {
            it.setWorkoutRecord(wr);
        }
    }
}
