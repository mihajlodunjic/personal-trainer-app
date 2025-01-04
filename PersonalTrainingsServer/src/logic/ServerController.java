/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Trainer;
import java.util.ArrayList;
import java.util.LinkedList;
import so.login.SOLogin;
import so.register.SORegister;
import so.trainer.SOGetAllTrainer;
import so.trainer.SOUpdateTrainer;

/**
 *
 * @author pc
 */
public class ServerController {
    private static ServerController instance;
    private ArrayList<Trainer> loggedInTrainers=new ArrayList<>();
    private ServerController(){
        
    } 
    public static ServerController getInstance(){
        if(instance == null) instance = new ServerController();
        return instance;
    }

    public ArrayList<Trainer> getLoggedInTrainers() {
        return loggedInTrainers;
    }
    
    public Trainer login(Trainer trainer) throws Exception{
        SOLogin so = new SOLogin();
        so.executeSO(trainer);
        return so.getLoggedIn();
    }
    
    public boolean register(Trainer trainer) throws Exception{
        SORegister so=new SORegister();
        so.executeSO(trainer);
        return so.isSuccess();
    }
    
    public LinkedList<Trainer> getAllTrainer(Trainer trainer) throws Exception{
        SOGetAllTrainer so=new SOGetAllTrainer();
        so.executeSO(trainer);
        return so.getList();
        
    }
    public Boolean updateTrainer(Trainer trainer) throws Exception{
        SOUpdateTrainer so=new SOUpdateTrainer();
        so.executeSO(trainer);
        return so.isSuccess();
    }
}
