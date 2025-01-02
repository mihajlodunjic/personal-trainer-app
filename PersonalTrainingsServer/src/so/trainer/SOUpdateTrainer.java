/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trainer;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Trainer;
import java.util.LinkedList;
import logic.ServerController;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOUpdateTrainer extends AbstractSO{
    private Boolean success;
    public Boolean isSuccess(){
        return success;
    }
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(!(ddo instanceof Trainer))
            throw new Exception("Prosledjeni objekat nije instanca Trainer-a");
        LinkedList<Trainer> trainers=ServerController.getInstance().getAllTrainer();
        Trainer updatedTrainer=(Trainer) ddo;
        for (Trainer trainer : trainers){
            if(updatedTrainer.getIdTrаiner()!=trainer.getIdTrаiner() &&
                    updatedTrainer.getUserName().equals(trainer.getUserName()))
                throw new Exception("Vec postoji trener sa tim korisnickim imenom.");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        success=DatabaseBroker.updateRow(ddo);
    }
    
}
