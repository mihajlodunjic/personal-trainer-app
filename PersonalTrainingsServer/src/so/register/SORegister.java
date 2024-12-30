/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.register;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Trainer;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SORegister extends AbstractSO{
    private boolean success;
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(!(ddo instanceof Trainer))
            throw new Exception("Objekat nije instanca Trainer-a");
        Trainer trainer=(Trainer)ddo;
        if(DatabaseBroker.doesExist(trainer))
            throw new Exception("Trener vec postoji u sistemu");
        
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Trainer trainer=(Trainer)ddo;
        if(!(DatabaseBroker.insertRow(trainer)))
            throw new Exception("Greska pri ubacivanju u bazu.");
        success=true;
    }

    public boolean isSuccess() {
        return success;
    }
    
}
