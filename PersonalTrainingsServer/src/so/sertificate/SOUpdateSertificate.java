package so.sertificate;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Sertificate;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOUpdateSertificate extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Sertificate))
            throw new Exception("Prosleđeni objekat nije Sertificate.");

        Sertificate s = (Sertificate) ddo;
        if (s.getIdSertificate() <= 0)
            throw new Exception("Nedostaje idSertificate za izmenu.");
        if (s.getName()==null || s.getName().isBlank()
         || s.getPublisher()==null || s.getPublisher().isBlank())
            throw new Exception("Naziv i izdavač su obavezni.");

        Sertificate probe = new Sertificate();
        probe.setSearchCondition("idSertificate <> " + s.getIdSertificate());
        LinkedList<Sertificate> all = (LinkedList<Sertificate>)(LinkedList<?>) DatabaseBroker.getInstance().select(probe);
        for (Sertificate x : all) {
            if (x.getName().equalsIgnoreCase(s.getName())
             && x.getPublisher().equalsIgnoreCase(s.getPublisher()))
                throw new Exception("Sertifikat sa tim nazivom i izdavačem već postoji.");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Sertificate s = (Sertificate) ddo;
        s.setSearchCondition("idSertificate=" + s.getIdSertificate());
        if (!DatabaseBroker.getInstance().updateRow(s))
            throw new Exception("Neuspešna izmena sertifikata.");
        success = true;
    }
}
