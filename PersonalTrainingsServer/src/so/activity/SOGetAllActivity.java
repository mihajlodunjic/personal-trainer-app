/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.activity;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Activity;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOGetAllActivity extends AbstractSO{
    private LinkedList<Activity> list;
    public LinkedList<Activity> getList() { return list; }
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Activity))
            throw new Exception("Prosledjeni objekat nije instanca klase Activity");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Activity a = (Activity) ddo;
//        a.setSearchCondition("1");
        list = (LinkedList<Activity>)(LinkedList<?>) DatabaseBroker.select(a);
    }
    
}
