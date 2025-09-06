package so.workoutrecord;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.WorkoutItem;
import domain.WorkoutRecord;
import java.util.*;
import so.abstractso.AbstractSO;

public class SOUpdateWorkoutRecord extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof WorkoutRecord))
            throw new Exception("Prosleđeni objekat nije instanca WorkoutRecord.");

        WorkoutRecord wr = (WorkoutRecord) ddo;

        if (wr.getIdWorkoutRecord() <= 0)
            throw new Exception("Nedostaje identifikator evidencije za izmenu.");

        if (wr.getStartTime() == null || wr.getEndTime() == null
                || !wr.getEndTime().isAfter(wr.getStartTime()))
            throw new Exception("Vreme završetka mora biti posle početka.");

        if (wr.getItems() == null || wr.getItems().isEmpty())
            throw new Exception("Evidencija mora imati bar jednu stavku.");

        // jedinstvenost itemSN u novim stavkama
        HashSet<Integer> seen = new HashSet<>();
        for (WorkoutItem it : wr.getItems()) {
            if (it.getItemSN() <= 0)
                throw new Exception("itemSN mora biti > 0.");
            if (!seen.add(it.getItemSN()))
                throw new Exception("Duplikat rednog broja stavke (itemSN=" + it.getItemSN() + ").");
            if (it.getActivity() == null || it.getActivity().getIdActivity() <= 0)
                throw new Exception("Svaka stavka mora imati ispravan idActivity.");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        WorkoutRecord wr = (WorkoutRecord) ddo;

        // 1) UPDATE header
        wr.setSearchCondition("idWorkoutRecord=" + wr.getIdWorkoutRecord());
        if (!DatabaseBroker.updateRow(wr))
            throw new Exception("Neuspešan update zaglavlja evidencije.");

        // 2) Ucitaj postojece stavke iz baze
        WorkoutItem probe = new WorkoutItem();
        probe.setSearchCondition("wi.idWorkoutRecord=" + wr.getIdWorkoutRecord()); 
        LinkedList<WorkoutItem> existingList =
                (LinkedList<WorkoutItem>)(LinkedList<?>) DatabaseBroker.select(probe);

        // Mapiraj po itemSN
        Map<Integer, WorkoutItem> existingBySn = new HashMap<>();
        for (WorkoutItem it : existingList) existingBySn.put(it.getItemSN(), it);

        Map<Integer, WorkoutItem> incomingBySn = new HashMap<>();
        for (WorkoutItem it : wr.getItems()) incomingBySn.put(it.getItemSN(), it);

        // 3) DELETE: stavke koje postoje u bazi a nema ih u novom setu
        for (Integer oldSn : existingBySn.keySet()) {
            if (!incomingBySn.containsKey(oldSn)) {
                WorkoutItem del = new WorkoutItem();
                del.setSearchCondition("idWorkoutRecord=" + wr.getIdWorkoutRecord() +
                                       " AND itemSN=" + oldSn); // DELETE/UPDATE ne koriste alias
                if (!DatabaseBroker.deleteRow(del))
                    throw new Exception("Brisanje stavke itemSN=" + oldSn + " nije uspelo.");
            }
        }

        // 4) INSERT ili UPDATE za presek/novi
        for (Map.Entry<Integer, WorkoutItem> e : incomingBySn.entrySet()) {
            int sn = e.getKey();
            WorkoutItem incoming = e.getValue();
            incoming.setWorkoutRecord(wr); // treba za insert i za update (setAttrValues ima idActivity itd.)

            if (!existingBySn.containsKey(sn)) {
                // INSERT nova stavka
                if (!DatabaseBroker.insertRow(incoming))
                    throw new Exception("Greška pri ubacivanju stavke itemSN=" + sn);
            } else {
                // UPDATE ako ima promena
                WorkoutItem old = existingBySn.get(sn);
                if (changed(old, incoming)) {
                    incoming.setSearchCondition("idWorkoutRecord=" + wr.getIdWorkoutRecord() +
                                                " AND itemSN=" + sn);
                    if (!DatabaseBroker.updateRow(incoming))
                        throw new Exception("Neuspešan update stavke itemSN=" + sn);
                }
            }
        }

        success = true;
    }

    // Uporedi relevantna polja stavke
    private boolean changed(WorkoutItem oldIt, WorkoutItem newIt) {
        boolean diffIntensity = !safeEq(oldIt.getIntensity(), newIt.getIntensity());
        boolean diffNumSer    = oldIt.getNumOfSeries() != newIt.getNumOfSeries();
        boolean diffMass      = Double.compare(oldIt.getMass(), newIt.getMass()) != 0;
        boolean diffComment   = !safeEq(oldIt.getComment(), newIt.getComment());
        int oldActId = (oldIt.getActivity() != null) ? oldIt.getActivity().getIdActivity() : 0;
        int newActId = (newIt.getActivity() != null) ? newIt.getActivity().getIdActivity() : 0;
        boolean diffAct       = oldActId != newActId;
        return diffIntensity || diffNumSer || diffMass || diffComment || diffAct;
    }

    private static boolean safeEq(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
