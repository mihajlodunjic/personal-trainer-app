/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Trainer;
import logic.ServerController;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOLogin extends AbstractSO{
    private Trainer loggedIn;
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(ddo instanceof Trainer){
            Trainer t=(Trainer)ddo;
            for (Trainer trainer : ServerController.getInstance().getLoggedInTrainers())
                if(t.getUserName().equals(trainer.getUserName()))
                    throw new Exception("Trener je vec ulogovan!");
            
        }
        else
            throw new Exception("Objekat nije instanca Trainer-a");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Trainer trainer=(Trainer) ddo;
        if (DatabaseBroker.findRowAndReturn(trainer)) {
            loggedIn = trainer;
            ServerController.getInstance().getLoggedInTrainers().add(trainer);
            return;
        }
        throw new Exception("Ne postoji trener sa zadatim vrednostima.");
    }
    public Trainer getLoggedIn(){
        return loggedIn;
    }
    
}
