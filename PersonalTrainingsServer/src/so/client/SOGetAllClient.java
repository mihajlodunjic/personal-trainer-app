/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.client;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Client;
import domain.Gym;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOGetAllClient extends AbstractSO{
    private LinkedList<Client>list;
    
    public LinkedList<Client> getList(){
        return list;
    }
    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if(!(ddo instanceof Client))
            throw new Exception("Prosledjeni objekat nije instanca klase Client");
    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Client client=(Client)ddo;
        list=(LinkedList<Client>)(LinkedList<?>)DatabaseBroker.getInstance().select(client);
        for(Client c : list){
            c.getGym().setSearchCondition("idGym=" + c.getGym().getIdGym());

            if(!DatabaseBroker.getInstance().findRowAndReturn(c.getGym()))
                throw new Exception("Neuspesno ucitavanje teretane u instancu klijenta.");
        }
    }
    
}
