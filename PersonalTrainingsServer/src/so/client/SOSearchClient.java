package so.client;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Client;
import domain.Gym;
import enums.Gender;
import java.sql.Date;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOSearchClient extends AbstractSO {
    private LinkedList<Client> list;
    public LinkedList<Client> getList() { return list; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Client))
            throw new Exception("Prosleđeni objekat nije Client.");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Client crit = (Client) ddo;

        StringBuilder where = new StringBuilder("1");

        if (crit.getName() != null && !crit.getName().isBlank())
            where.append(" AND c_name LIKE '%").append(escape(crit.getName())).append("%'");
        if (crit.getLastName() != null && !crit.getLastName().isBlank())
            where.append(" AND c_lastName LIKE '%").append(escape(crit.getLastName())).append("%'");
        if (crit.getMobilePhone() != null && !crit.getMobilePhone().isBlank())
            where.append(" AND c_mobilePhone LIKE '%").append(escape(crit.getMobilePhone())).append("%'");
        if (crit.getGender() != null)
            where.append(" AND gender='").append(crit.getGender().getSerbianName()).append("'");

        if (crit.getBirthday() != null)
            where.append(" AND birthday='").append(Date.valueOf(crit.getBirthday())).append("'");

        if (crit.getGym() != null && crit.getGym().getIdGym() > 0)
            where.append(" AND idGym=").append(crit.getGym().getIdGym());

        crit.setSearchCondition(where.toString());

        @SuppressWarnings("unchecked")
        LinkedList<Client> result = (LinkedList<Client>)(LinkedList<?>) DatabaseBroker.getInstance().select(crit);

        for (Client c : result) {
            c.getGym().setSearchCondition("idGym=" + c.getGym().getIdGym());
            if (!DatabaseBroker.getInstance().findRowAndReturn(c.getGym()))
                throw new Exception("Neuspešno učitavanje teretane za klijenta id=" + c.getIdClient());
        }

        list = result;
    }

    private static String escape(String s) {
        return s.replace("'", "''");
    }
}
