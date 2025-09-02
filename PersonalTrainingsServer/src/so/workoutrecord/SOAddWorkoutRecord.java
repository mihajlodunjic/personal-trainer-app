package so.workoutrecord;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.WorkoutRecord;
import domain.WorkoutItem;
import java.time.LocalTime;
import java.util.List;
import so.abstractso.AbstractSO;

public class SOAddWorkoutRecord extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof WorkoutRecord))
            throw new Exception("Prosledjeni objekat nije instanca WorkoutRecord");
        WorkoutRecord wr = (WorkoutRecord) ddo;

        if (wr.getTrainer()==null || wr.getClient()==null)
            throw new Exception("Nedostaje trener ili klijent u evidenciji");
        if (wr.getStartTime()==null || wr.getEndTime()==null)
            throw new Exception("Nedostaju vremena početka/kraja");
        if (!wr.getEndTime().isAfter(wr.getStartTime()))
            throw new Exception("Vreme završetka mora biti posle početka");
        List<WorkoutItem> items = wr.getItems();
        if (items==null || items.isEmpty())
            throw new Exception("Evidencija mora sadržati bar jednu stavku");
        for (WorkoutItem it : items) {
            if (it.getActivity()==null || it.getActivity().getIdActivity()<=0)
                throw new Exception("Svaka stavka mora imati aktivnost (idActivity)");
            if (it.getItemSN()<=0) throw new Exception("Stavka mora imati redni broj (itemSN>0)");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        WorkoutRecord wr = (WorkoutRecord) ddo;

        //upis zaglavlja i dohvatanje ID-a
        int newId = DatabaseBroker.insertRowReturnKey(wr);
        wr.setIdWorkoutRecord(newId);

        //upis stavki
        for (WorkoutItem it : wr.getItems()) {
            it.setWorkoutRecord(wr);
            if (!DatabaseBroker.insertRow(it))
                throw new Exception("Greška pri ubacivanju stavke #" + it.getItemSN());
        }
        success = true;
    }
}
