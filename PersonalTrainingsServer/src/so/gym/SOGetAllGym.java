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
public class SOGetAllGym extends AbstractSO{
    LinkedList<Gym>list;
    public LinkedList<Gym> getList() {
        return list;
    }
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(!(ddo instanceof Gym))
            throw new Exception("Prosledjeni objekat nije instanca klase Gym.");
        
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Gym gym=(Gym)ddo;
        list=(LinkedList<Gym>)(LinkedList<?>)DatabaseBroker.getInstance().select(gym);
    }
    
}
