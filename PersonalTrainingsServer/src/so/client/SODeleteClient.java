package so.client;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Client;
import domain.WorkoutItem;
import domain.WorkoutRecord;
import so.abstractso.AbstractSO;

public class SODeleteClient extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Client))
            throw new Exception("Prosleđeni objekat nije Client.");
        Client c = (Client) ddo;
        if (c.getIdClient() <= 0)
            throw new Exception("Nedostaje ispravan idClient za brisanje.");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Client c = (Client) ddo;
        int clientId = c.getIdClient();

        //prvo brisemo sve stavke za svaku evidenciju izabranog klijenta
        WorkoutItem wiProbe = new WorkoutItem();
        wiProbe.setSearchCondition("idWorkoutRecord IN (SELECT idWorkoutRecord FROM workoutrecord WHERE idClient=" + clientId + ")");
        //moze se desiti da klijent nema ni jednu stavku na svim treninzima (ili nema treninge) pa ne proveravamo da li metoda vraca false
        DatabaseBroker.getInstance().deleteRow(wiProbe);

        //sledece brisemo sve evidencije 
        WorkoutRecord wrProbe = new WorkoutRecord();
        wrProbe.setSearchCondition("idClient=" + clientId);
        //isto moze biti 0 treninga
        DatabaseBroker.getInstance().deleteRow(wrProbe);

        //brisanje samog klijenta
        c.setSearchCondition("idClient=" + clientId);
        boolean clientDeleted = DatabaseBroker.getInstance().deleteRow(c);
        if (!clientDeleted)
            throw new Exception("Brisanje klijenta nije uspelo (ne postoji ili je već obrisan).");

        success = true;
    }
}
