// server/so/trainersertificate/SOGetTrainerSertificates.java
package so.trainersertificate;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Trainer;
import domain.TrainerSertificate;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

public class SOGetTrainerSertificates extends AbstractSO {
    private LinkedList<TrainerSertificate> list;
    public LinkedList<TrainerSertificate> getList() { return list; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Trainer))
            throw new Exception("Ocekivan je Trainer kao kriterijum.");
        //dozvoljavamo id=0 svi treneri
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Trainer t = (Trainer) ddo;
        TrainerSertificate probe = new TrainerSertificate();
        String where = (t.getIdTrаiner() > 0) ? ("ts.idTrainer=" + t.getIdTrаiner()) : "1";
        probe.setSearchCondition(where);

        LinkedList<TrainerSertificate> res =
            (LinkedList<TrainerSertificate>)(LinkedList<?>) DatabaseBroker.getInstance().select(probe);
        list = res;
    }
}
