/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.client;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Client;
import java.util.LinkedList;
import java.util.List;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOAddClient extends AbstractSO{

    private boolean success=false;
    
    public boolean isSuccess(){
        return success;
    }
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(!(ddo instanceof Client))
            throw new Exception("Prosledjeni objekat nije instanca klase Client");
        ddo.setSearchCondition("1");
        LinkedList<Client> list=(LinkedList<Client>)(LinkedList<?>)DatabaseBroker.getInstance().select(ddo);
        Client client=(Client)ddo;
        for(Client c : list){
            if(c.getMobilePhone().equals(client.getMobilePhone()))
                throw new Exception("Sistem ne može da zamapti klijenta");
        }
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Client c=(Client)ddo;
        if(!DatabaseBroker.getInstance().insertRow(ddo))
            throw new Exception("Greska pri ubacivanju u bazu!");
        success=true;
    }
    
}
