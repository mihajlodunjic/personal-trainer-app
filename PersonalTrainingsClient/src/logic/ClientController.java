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
import domain.*;
import dto.WorkoutRecordCriteria;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import session.ClientSession;

/**
 *
 * @author pc
 */
public class ClientController {

    private static ClientController instance = null;

    private ClientController() throws Exception {

    }

    public static ClientController getInstance() throws Exception {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    //User operations
    public Trainer login(Trainer trainer) throws Exception {
        return (Trainer) sendRequest(Operation.LOGIN, trainer);

    }

    public void logout(Trainer trainer) throws Exception {
        sendRequest(Operation.LOGOUT, trainer);
    }

    public boolean register(Trainer trainer) throws Exception {
        return (Boolean) sendRequest(Operation.REGISTER, trainer);
    }

    public LinkedList<Trainer> getAllTrainer() throws Exception {
        return (LinkedList<Trainer>) sendRequest(Operation.GET_ALL_TRAINER, null);
    }

    public boolean updateTrainer(Trainer trainer) throws Exception {
        return (Boolean) sendRequest(Operation.UPDATE_TRAINER, trainer);
    }

    //Client operations
    public boolean addClient(Client client) throws Exception {
        return (Boolean) sendRequest(Operation.INSERT_CLIENT, client);
    }

    public LinkedList<Client> getAllClient(Client client, String criteria) throws Exception {
        client.setSearchCondition(criteria);
        return (LinkedList<Client>) sendRequest(Operation.GET_ALL_CLIENT, client);
    }

    public LinkedList<Client> searchClients(Client client) throws Exception {
        return (LinkedList<Client>) sendRequest(Operation.SEARCH_CLIENT, client);
    }

    public boolean updateClient(Client client) throws Exception {
        return (Boolean) sendRequest(Operation.UPDATE_CLIENT, client);
    }

    public boolean deleteClient(Client client) throws Exception {
        return (Boolean) sendRequest(Operation.DELETE_CLIENT, client);
    }

    //gym operations
    public LinkedList<Gym> getAllGym(Gym gym, String criteria) throws Exception {
        gym.setSearchCondition(criteria);
        return (LinkedList<Gym>) sendRequest(Operation.GET_ALL_GYM, gym);
    }

    public boolean addGym(Gym gym) throws Exception {
        return (Boolean) sendRequest(Operation.INSERT_GYM, gym);
    }

    //activity operations
    public LinkedList<Activity> getAllActivity(Activity activity, String criteria) throws Exception {
        activity.setSearchCondition(criteria);
        return (LinkedList<Activity>) sendRequest(Operation.GET_ALL_ACTIVITY, activity);
    }

    public boolean addActivity(Activity activity) throws Exception {
        return (Boolean) sendRequest(Operation.INSERT_ACTIVITY, activity);
    }
    //workout record operaitons

    public boolean addWorkoutRecord(WorkoutRecord wr) throws Exception {
        return (Boolean) sendRequest(Operation.INSERT_WORKOUT_RECORD, wr);
    }

    public LinkedList<WorkoutRecord> getWorkoutRecordsForClient(Client client) throws Exception {
        WorkoutRecordCriteria wrc = new WorkoutRecordCriteria();
        wrc.setClientId(client.getIdClient());
        return (LinkedList<WorkoutRecord>) sendRequest(Operation.SEARCH_WORKOUT_RECORDS, wrc);
    }

    public boolean updateWorkoutRecord(WorkoutRecord wr) throws Exception {
        return (Boolean) sendRequest(Operation.UPDATE_WORKOUT_RECORD, wr);
    }

    public boolean deleteWorkoutRecord(WorkoutRecord wr) throws Exception {
        return (Boolean) sendRequest(Operation.DELETE_WORKOUT_RECORD, wr);
    }

    //workout items operations
    public LinkedList<WorkoutItem> getItemsForWorkoutRecord(WorkoutRecord wr) throws Exception {
        return (LinkedList<WorkoutItem>) sendRequest(Operation.GET_WORKOUT_ITEMS_FOR_RECORD, wr);
    }
    //sertificate operations

    public boolean addSertificate(Sertificate sertificate) throws Exception {
        return (Boolean) sendRequest(Operation.INSERT_SERTIFICATE, sertificate);
    }

    public boolean updateSertificate(Sertificate sertificate) throws Exception {
        return (Boolean) sendRequest(Operation.UPDATE_SERTIFICATE, sertificate);
    }

    public LinkedList<Sertificate> getAllSertificate() throws Exception {
        return (LinkedList<Sertificate>) sendRequest(Operation.GET_ALL_SERTIFICATE, new Sertificate());
    }
    //trainer sertificate operations
    
    public LinkedList<TrainerSertificate> getTrainerSertificateForTrainer(Trainer trainer)throws Exception{
        return (LinkedList<TrainerSertificate>)sendRequest(Operation.GET_TRAINER_SERTIFICATES, trainer);
    }
    
    public boolean addTrainerSertificate(TrainerSertificate trainerSertificate)throws Exception{
        return (Boolean)sendRequest(Operation.INSERT_TRAINER_SERTIFICATE, trainerSertificate);
    }
    
    private Object sendRequest(Operation operation, Object arg) throws Exception {
        Request request = new Request(operation, arg);

        ClientSession.getInstance().getSender().send(request);
        Response response = (Response) ClientSession.getInstance().getReceiver().receive();
        if (response.getException() != null) {
            throw response.getException();
        }
        return response.getResult();
    }
    
    

}
