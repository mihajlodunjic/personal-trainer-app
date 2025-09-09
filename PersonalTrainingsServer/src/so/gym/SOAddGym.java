package so.gym;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Gym;
import so.abstractso.AbstractSO;

public class SOAddGym extends AbstractSO {
    private boolean success = false;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Gym))
            throw new Exception("Prosleđeni objekat nije instanca klase Gym.");

        Gym gym = (Gym) ddo;

        if (gym.getName() == null || gym.getName().trim().isEmpty())
            throw new Exception("Naziv teretane je obavezan.");
        if (gym.getAddress() == null || gym.getAddress().trim().isEmpty())
            throw new Exception("Adresa teretane je obavezna.");
        if (gym.getEquipmentLevel() == null)
            throw new Exception("Nivo opremljenosti je obavezan.");
        if (gym.getMobilePhone() == null || gym.getMobilePhone().trim().isEmpty())
            throw new Exception("Broj telefona teretane je obavezan.");

        

        String nameNorm = esc(gym.getName().trim());
        String addrNorm = esc(gym.getAddress().trim());

        Gym probe = new Gym();
        probe.setSearchCondition(
            "LOWER(TRIM(g_name)) = LOWER(TRIM('" + nameNorm + "')) " +
            "AND LOWER(TRIM(address)) = LOWER(TRIM('" + addrNorm + "'))"
        );
        if (DatabaseBroker.getInstance().doesExist(probe))
            throw new Exception("Uneta teretana već postoji u sistemu (naziv i adresa).");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Gym gym = (Gym) ddo;

        if (!DatabaseBroker.getInstance().insertRow(gym))
            throw new Exception("Greška pri ubacivanju teretane u bazu.");

        success = true;
    }

    private static String esc(String s) {
        return s == null ? null : s.replace("'", "''");
    }
}
