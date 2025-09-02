/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Request;
import communication.Response;
import communication.Operation;
import communication.Receiver;
import communication.Sender;
import domain.*;
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
public class ClientThread extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private Trainer trainer;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            receiver = new Receiver(socket);
            sender = new Sender(socket);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private Response handleReq(Request r) {
        Response response = new Response(null, null);
        Boolean success;
        Trainer trainer;

        try {
            switch (r.getOperation()) {
                case Operation.LOGIN:
                    trainer = (Trainer) r.getArgument();
                    trainer = ServerController.getInstance().login(trainer);
                    response.setResult(trainer);
                    break;
                case Operation.LOGOUT:
                    trainer = (Trainer) r.getArgument();
                    ServerController.getInstance().logout(trainer);
                    break;
                case Operation.REGISTER:
                    success = ServerController.getInstance().register((Trainer) r.getArgument());
                    response.setResult(success);
                    break;
                case Operation.GET_ALL_TRAINER:
                    LinkedList<Trainer> list = ServerController.getInstance().getAllTrainer(new Trainer());
                    response.setResult(list);
                    break;
                case Operation.UPDATE_TRAINER:
                    success = ServerController.getInstance().updateTrainer((Trainer) r.getArgument());
                    response.setResult(success);
                    break;

                //client operations    
                case Operation.INSERT_CLIENT:
                    success = ServerController.getInstance().addClient((Client) r.getArgument());
                    response.setResult(success);
                    break;
                case Operation.GET_ALL_CLIENT:
                    LinkedList<Client> clientList = ServerController.getInstance().getAllClient((Client) r.getArgument());
                    response.setResult(clientList);
                    break;

                //gym operations
                case Operation.INSERT_GYM:
                    success = ServerController.getInstance().addGym((Gym) r.getArgument());
                    response.setResult(success);
                    break;
                case Operation.GET_ALL_GYM:
                    LinkedList<Gym> gymList = ServerController.getInstance().getAllGym((Gym) r.getArgument());
                    response.setResult(gymList);
                    break;
                case Operation.GET_ALL_ACTIVITY:
                    LinkedList<Activity> activities = ServerController.getInstance().getAllActivity(new Activity());
                    response.setResult(activities);
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
            while (!socket.isClosed()) {

                Request request = (Request) receiver.receive();
                Response response = handleReq(request);
                sender.send(response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
