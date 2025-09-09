package so.activity;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Activity;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOUpdateActivity extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Activity))
            throw new Exception("Prosleđeni objekat nije Activity.");
        Activity a = (Activity) ddo;
        if (a.getIdActivity() <= 0)
            throw new Exception("Nedostaje idActivity za izmenu.");
        if (a.getCategory() == null || a.getName() == null || a.getName().isBlank())
            throw new Exception("Kategorija i naziv aktivnosti su obavezni.");

        
        Activity probe = new Activity();
        probe.setSearchCondition("idActivity <> " + a.getIdActivity());
        LinkedList<Activity> all = (LinkedList<Activity>)(LinkedList<?>) DatabaseBroker.getInstance().select(probe);
        for (Activity x : all) {
            if (x.getCategory() == a.getCategory()
                && x.getName().equalsIgnoreCase(a.getName())) {
                throw new Exception("Aktivnost sa tim nazivom i kategorijom već postoji.");
            }
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Activity a = (Activity) ddo;
        a.setSearchCondition("idActivity=" + a.getIdActivity());
        if (!DatabaseBroker.getInstance().updateRow(a))
            throw new Exception("Neuspešna izmena aktivnosti.");
        success = true;
    }
}
