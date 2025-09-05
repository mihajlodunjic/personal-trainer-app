package so.workoutrecord;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.WorkoutItem;
import domain.WorkoutRecord;
import so.abstractso.AbstractSO;

public class SODeleteWorkoutRecord extends AbstractSO {
    private boolean success=false;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof WorkoutRecord))
            throw new Exception("Prosleđeni objekat nije WorkoutRecord.");
        WorkoutRecord wr = (WorkoutRecord) ddo;
        if (wr.getIdWorkoutRecord() <= 0)
            throw new Exception("Nedostaje ispravan idWorkoutRecord za brisanje.");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        WorkoutRecord wr = (WorkoutRecord) ddo;

        WorkoutItem itemProbe = new WorkoutItem();
        itemProbe.setSearchCondition("idWorkoutRecord=" + wr.getIdWorkoutRecord());
        boolean itemsDeleted = DatabaseBroker.deleteRow(itemProbe);

        wr.setSearchCondition("idWorkoutRecord=" + wr.getIdWorkoutRecord());
        boolean recordDeleted = DatabaseBroker.deleteRow(wr);

        if (!(itemsDeleted && recordDeleted))
            throw new Exception("Brisanje evidencije nije uspelo.");

        success = true;
    }
}

