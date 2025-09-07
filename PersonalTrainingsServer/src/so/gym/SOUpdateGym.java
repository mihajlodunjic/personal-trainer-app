package so.gym;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Gym;
import so.abstractso.AbstractSO;

public class SOUpdateGym extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Gym))
            throw new Exception("Prosleđeni objekat nije instanca klase Gym.");

        Gym g = (Gym) ddo;

        if (g.getIdGym() <= 0)
            throw new Exception("Nedostaje identifikator teretane za izmenu.");

        if (g.getName() == null || g.getName().trim().isEmpty())
            throw new Exception("Naziv teretane je obavezan.");
        if (g.getAddress() == null || g.getAddress().trim().isEmpty())
            throw new Exception("Adresa je obavezna.");
        if (g.getEquipmentLevel() == null)
            throw new Exception("Nivo opremljenosti je obavezan.");
        if (g.getMobilePhone() == null || g.getMobilePhone().trim().isEmpty())
            throw new Exception("Telefon je obavezan.");
        if (g.getEmail() == null || g.getEmail().trim().isEmpty())
            throw new Exception("Email je obavezan.");
        if (!g.getEmail().trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new Exception("Email nije ispravan.");

        Gym probe1 = new Gym();
        probe1.setSearchCondition(
                "idGym <> " + g.getIdGym() +
                " AND LOWER(TRIM(g_name)) = LOWER(TRIM('" + esc(g.getName().trim()) + "'))" +
                " AND LOWER(TRIM(address)) = LOWER(TRIM('" + esc(g.getAddress().trim()) + "'))"
        );
        if (DatabaseBroker.doesExist(probe1))
            throw new Exception("Teretana sa istim nazivom i adresom već postoji.");

        
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Gym g = (Gym) ddo;

        g.setSearchCondition("idGym=" + g.getIdGym());

        if (!DatabaseBroker.updateRow(g))
            throw new Exception("Neuspešna izmena teretane.");

        success = true;
    }

    private static String esc(String s) { return s.replace("'", "''"); }
}
