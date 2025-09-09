package so.abstractso;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import java.sql.SQLException;

public abstract class AbstractSO {
    
    protected abstract void validate(DefaultDomainObject ddo) throws Exception;
    protected abstract void execute(DefaultDomainObject ddo) throws Exception;

    public void executeSO(DefaultDomainObject ddo) throws Exception {
        try {
            validate(ddo);
            execute(ddo);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DatabaseBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DatabaseBroker.getInstance().getConnection().rollback();
    }
}
