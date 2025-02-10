/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gym;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Gym;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOAddGym extends AbstractSO{
    private boolean success=false;
    public boolean isSuccess(){
        return success;
    }
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(!(ddo instanceof Gym))
            throw new Exception("Prosledjeni objekat nije instanca klase Gym.");
        Gym gym=(Gym)ddo;
        ddo.setSearchCondition("1");
        LinkedList<Gym> list=(LinkedList<Gym>)(LinkedList<?>)DatabaseBroker.select(ddo);
        for (Gym g : list){
            if(g.getAddress().equals(gym.getAddress()) && g.getName().equals(gym.getName()))
                throw new Exception("Uneta teretana vec postoji u sistemu.");
        }
        
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Gym gym=(Gym)ddo;
        if(!(DatabaseBroker.insertRow(gym)))
            throw new Exception("Greska pri ubacivanju u bazu");
        success=true;
    }
    
}
