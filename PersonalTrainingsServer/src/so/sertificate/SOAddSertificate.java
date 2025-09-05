package so.sertificate;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Sertificate;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOAddSertificate extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Sertificate))
            throw new Exception("Prosleđeni objekat nije Sertificate.");

        Sertificate s = (Sertificate) ddo;
        if (s.getName()==null || s.getName().isBlank()
         || s.getPublisher()==null || s.getPublisher().isBlank())
            throw new Exception("Naziv i izdavač su obavezni.");

        Sertificate probe = new Sertificate();
        probe.setSearchCondition("1");
        LinkedList<Sertificate> all = (LinkedList<Sertificate>)(LinkedList<?>) DatabaseBroker.select(probe);
        for (Sertificate x : all) {
            if (x.getName().equalsIgnoreCase(s.getName())
             && x.getPublisher().equalsIgnoreCase(s.getPublisher()))
                throw new Exception("Sertifikat sa tim nazivom i izdavačem već postoji.");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        if (!DatabaseBroker.insertRow(ddo))
            throw new Exception("Neuspešno dodavanje sertifikata.");
        success = true;
    }
}
