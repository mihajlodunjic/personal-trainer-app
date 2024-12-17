/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Trainer;
import utils.DatabaseBroker;

/**
 *
 * @author pc
 */
public class Controller {
    private static Controller instance=null;
    
    private Controller(){
        
    }
    public static Controller getInstance(){
        if(instance==null)
            instance=new Controller();
        return instance;
    }
    public Trainer login(String userName, String password){
        Trainer trainer=new Trainer();
        boolean exists=DatabaseBroker.doesExist(trainer);
        
        if(exists){
            System.out.println("nadjen");
            boolean found=DatabaseBroker.findRowAndReturn(trainer);
            
            if(found){
                System.out.println("vracen iz baze");
                return trainer;
            }
        }
        
        return null;
    }
    
    public boolean register(String userName, String password, String name, String lastName){
        Trainer trainer=new Trainer();
        trainer.setUserName(userName);
        trainer.setSearchCondition("userName='"+userName+"'");
        if(DatabaseBroker.doesExist(trainer)){
            return false;
        }
        trainer.setPassword(password);
        trainer.setName(name);
        trainer.setLastName(lastName);
        return DatabaseBroker.insertRow(trainer);
    }
    
}
