/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import abstractClass.DefaultDomainObject;
import domain.Trainer;
import java.util.LinkedList;
import logic.ServerController;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOLogout extends AbstractSO{

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(ddo instanceof Trainer){
            Trainer t=(Trainer)ddo;
            boolean found=false;
            for (Trainer trainer : ServerController.getInstance().getLoggedInTrainers())
                if(t.getUserName().equals(trainer.getUserName())){
                    found=true;
                    break;
                }
            if(!found)
                throw new Exception("Ovaj trener nije prijavljen na sistem.");
            
        }
        else
            throw new Exception("Objekat nije instanca Trainer-a");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        LinkedList<Trainer>list=ServerController.getInstance().getLoggedInTrainers();
        Trainer trainer=(Trainer)ddo;
        int i =0;
        for(i=0;i<list.size();i++){
            if(trainer.getUserName().equals(list.get(i).getUserName()))
                break;
        }
        ServerController.getInstance().getLoggedInTrainers().remove(i);
    }
    
}
