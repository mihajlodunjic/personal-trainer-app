/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trainer;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Trainer;
import java.util.ArrayList;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOGetAllTrainer extends AbstractSO{
    private LinkedList<Trainer> list;
    public LinkedList<Trainer> getList(){
        return list;
    }
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(!(ddo instanceof Trainer))
            throw new Exception("Objekat nije instanca Trainer-a");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        LinkedList<DefaultDomainObject> trainers= DatabaseBroker.getInstance().select(ddo);
        list=(LinkedList<Trainer>) (LinkedList<?>) trainers;
    }
}
