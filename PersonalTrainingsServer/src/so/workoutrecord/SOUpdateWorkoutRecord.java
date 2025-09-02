package so.workoutrecord;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.WorkoutItem;
import domain.WorkoutRecord;
import java.util.List;
import so.abstractso.AbstractSO;

public class SOUpdateWorkoutRecord extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof WorkoutRecord))
            throw new Exception("Prosledjeni objekat nije instanca WorkoutRecord");
        WorkoutRecord wr = (WorkoutRecord) ddo;
        if (wr.getIdWorkoutRecord()<=0)
            throw new Exception("Nedostaje identifikator evidencije za izmenu");
        if (!wr.getEndTime().isAfter(wr.getStartTime()))
            throw new Exception("Vreme završetka mora biti posle početka");
        List<WorkoutItem> items = wr.getItems();
        if (items==null || items.isEmpty())
            throw new Exception("Evidencija mora imati bar jednu stavku");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        WorkoutRecord wr = (WorkoutRecord) ddo;

        //UPDATE header
        wr.setSearchCondition("idWorkoutRecord=" + wr.getIdWorkoutRecord());
        if (!DatabaseBroker.updateRow(wr))
            throw new Exception("Neuspešan update zaglavlja evidencije");

        //DELETE sve stavke za dati record
        WorkoutItem del = new WorkoutItem();
        del.setSearchCondition("idWorkoutRecord=" + wr.getIdWorkoutRecord());
        if (!DatabaseBroker.deleteRow(del))
            throw new Exception("Neuspešno brisanje starih stavki");

        //INSERT nove stavke
        for (WorkoutItem it : wr.getItems()) {
            it.setWorkoutRecord(wr);
            if (!DatabaseBroker.insertRow(it))
                throw new Exception("Greška pri ubacivanju stavke #" + it.getItemSN());
        }
        success = true;
    }
}
