package so.client;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Client;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOUpdateClient extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Client))
            throw new Exception("Prosleđeni objekat nije Client.");
        Client updated = (Client) ddo;

        if (updated.getIdClient() <= 0)
            throw new Exception("Nedostaje idClient za izmenu.");

        Client probe = new Client();
        probe.setSearchCondition("idClient <> " + updated.getIdClient());
        LinkedList<Client> all = (LinkedList<Client>)(LinkedList<?>) DatabaseBroker.getInstance().select(probe);
        for (Client c : all) {
            if (c.getMobilePhone().equals(updated.getMobilePhone()))
                throw new Exception("Klijent sa tim brojem telefona već postoji.");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Client updated = (Client) ddo;
        updated.setSearchCondition("idClient=" + updated.getIdClient());
        if (!DatabaseBroker.getInstance().updateRow(updated))
            throw new Exception("Neuspešna izmena klijenta.");
        success = true;
    }
}
