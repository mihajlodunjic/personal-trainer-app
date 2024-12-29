/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Trainer;
import database.DatabaseBroker;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author pc
 */
public class ClientController {
    private static ClientController instance=null;
    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    private ClientController()throws IOException{
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    public static ClientController getInstance() throws IOException{
        if(instance==null)
            instance=new ClientController();
        return instance;
    }
    public Trainer login(String userName, String password)throws Exception{
        Trainer trainer=new Trainer();
        trainer.setUserName(userName);
        trainer.setPassword(password);
        trainer.setSearchCondition("username='"+userName+"' AND password='"+password+"'");
        Request request=new Request(Operation.LOGIN, trainer);
        sender.send(request);
        
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            System.out.println((Trainer)response.getResult());
            return (Trainer)response.getResult();
        }
        else {
            response.getException().printStackTrace();
            return null;
        }
        
//        trainer.setUserName(userName);
//        trainer.setPassword(password);
//        trainer.setSearchCondition("username='"+trainer.getUserName()+"' AND password='"+password+"'");
//        boolean exists=DatabaseBroker.doesExist(trainer);
//        
//        if(exists){
//            System.out.println("nadjen");
//            trainer.setSearchCondition("username='"+trainer.getUserName()+"'");
//            boolean found=DatabaseBroker.findRowAndReturn(trainer);
//            
//            if(found){
//                System.out.println("vracen iz baze");
//                return trainer;
//            }
//        }
//        
//        return null;
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
    public Trainer login(Trainer trainer)throws Exception{
        return (Trainer) sendRequest(Operation.LOGIN, trainer);
        
    }
    private Object sendRequest(Operation operation, Object arg)throws Exception{
        Request request=new Request(operation, arg);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()!=null)
            throw response.getException();
        return response.getResult();
    }
}
