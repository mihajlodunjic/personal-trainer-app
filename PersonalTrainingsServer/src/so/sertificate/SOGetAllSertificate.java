package so.sertificate;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Sertificate;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOGetAllSertificate extends AbstractSO {
    private LinkedList<Sertificate> list;
    public LinkedList<Sertificate> getList() { return list; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Sertificate))
            throw new Exception("Prosleđeni objekat nije Sertificate.");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Sertificate s = (Sertificate) ddo;
        s.setSearchCondition("1");
        list = (LinkedList<Sertificate>)(LinkedList<?>) DatabaseBroker.getInstance().select(s);
    }
}
