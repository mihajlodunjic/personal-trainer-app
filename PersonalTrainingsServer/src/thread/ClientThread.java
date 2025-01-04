/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Request;
import communication.Response;
import communication.Operation;
import domain.Trainer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import logic.ServerController;
import so.trainer.SOUpdateTrainer;

/**
 *
 * @author pc
 */
public class ClientThread extends Thread{
    private Socket socket;
    
    public ClientThread(Socket socket){
        this.socket=socket;
        
    }
    private Response handleReq(Request r){
        Response response=new Response(null, null);
        Boolean success;
        Trainer trainer;
        try {
            switch(r.getOperation()){
                case Operation.LOGIN:
                    trainer=(Trainer)r.getArgument();
                    trainer= ServerController.getInstance().login(trainer);
                    response.setResult(trainer);
                    break;
                case Operation.LOGOUT:
                    trainer=(Trainer)r.getArgument();
                    ServerController.getInstance().logout(trainer);
                    break;
                case Operation.REGISTER:
                    success=ServerController.getInstance().register((Trainer)r.getArgument());
                    response.setResult(success);
                    break;
                case Operation.GET_ALL_TRAINER:
                    LinkedList<Trainer>list=ServerController.getInstance().getAllTrainer(new Trainer());
                    response.setResult(list);
                    break;
                case Operation.UPDATE_TRAINER:
                    success=ServerController.getInstance().updateTrainer((Trainer)r.getArgument());
                    response.setResult(success);
                    break;
                default:
                    return null;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        
        
        return response;
    }
    @Override
    public void run() {
        try {
            while(!socket.isClosed()){
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleReq(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
