package so.trainersertificate;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.TrainerSertificate;
import so.abstractso.AbstractSO;

public class SOAddTrainerSertificate extends AbstractSO {
    private boolean success;
    public boolean isSuccess() { return success; }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof TrainerSertificate))
            throw new Exception("Prosleđeni objekat nije TrainerSertificate.");

        TrainerSertificate ts = (TrainerSertificate) ddo;

        if (ts.getTrainer() == null || ts.getTrainer().getIdTrаiner() <= 0)
            throw new Exception("Nedostaje ispravan id trenera.");
        if (ts.getSertificate() == null || ts.getSertificate().getIdSertificate() <= 0)
            throw new Exception("Nedostaje ispravan id sertifikata.");

        //proveri duplikat
        TrainerSertificate probe = new TrainerSertificate(ts.getTrainer(), ts.getSertificate());
        probe.setSearchCondition("idTrainer=" + ts.getTrainer().getIdTrаiner() +
                                 " AND idSertificate=" + ts.getSertificate().getIdSertificate());
        if (DatabaseBroker.getInstance().doesExist(probe))
            throw new Exception("Taj sertifikat je već dodeljen treneru.");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        if (!DatabaseBroker.getInstance().insertRow(ddo))
            throw new Exception("Neuspešno dodeljivanje sertifikata treneru.");
        success = true;
    }
}
