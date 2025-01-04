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
import java.util.LinkedList;

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
    
    public Trainer login(Trainer trainer) throws Exception{
        return (Trainer) sendRequest(Operation.LOGIN, trainer);
        
    }
    public void logout(Trainer trainer) throws Exception{
        sendRequest(Operation.LOGOUT, trainer);
    }
    public boolean register(Trainer trainer) throws Exception{
        return (Boolean) sendRequest(Operation.REGISTER, trainer);
    }
    
    public LinkedList<Trainer> getAllTrainer() throws Exception{
        return (LinkedList<Trainer>) sendRequest(Operation.GET_ALL_TRAINER, null);
    }
    
    public boolean updateTrainer(Trainer trainer) throws Exception{
        return (Boolean)sendRequest(Operation.UPDATE_TRAINER, trainer);
    }
    private Object sendRequest(Operation operation, Object arg) throws Exception{
        Request request=new Request(operation, arg);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()!=null)
            throw response.getException();
        return response.getResult();
    }
}
