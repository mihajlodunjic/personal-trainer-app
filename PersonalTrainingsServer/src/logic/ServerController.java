/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.*;
import dto.WorkoutRecordCriteria;
import java.util.LinkedList;
import so.activity.SOAddActivity;
import so.activity.SOGetAllActivity;
import so.activity.SOUpdateActivity;
import so.client.SOAddClient;
import so.client.SODeleteClient;
import so.client.SOGetAllClient;
import so.client.SOSearchClient;
import so.client.SOUpdateClient;
import so.gym.SOAddGym;
import so.gym.SOGetAllGym;
import so.gym.SOUpdateGym;
import so.login.SOLogin;
import so.login.SOLogout;
import so.register.SORegister;
import so.sertificate.SOAddSertificate;
import so.sertificate.SOGetAllSertificate;
import so.sertificate.SOUpdateSertificate;
import so.trainer.SOGetAllTrainer;
import so.trainer.SOUpdateTrainer;
import so.trainersertificate.SOAddTrainerSertificate;
import so.trainersertificate.SOGetTrainerSertificates;
import so.workoutrecord.SOAddWorkoutRecord;
import so.workoutrecord.SODeleteWorkoutRecord;
import so.workoutrecord.SOSearchWorkoutRecords;
import so.workoutrecord.SOUpdateWorkoutRecord;

/**
 *
 * @author pc
 */
public class ServerController {

    private static ServerController instance;
    private LinkedList<Trainer> loggedInTrainers = new LinkedList<>();

    private ServerController() {

    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public LinkedList<Trainer> getLoggedInTrainers() {
        return loggedInTrainers;
    }

    public Trainer login(Trainer trainer) throws Exception {
        SOLogin so = new SOLogin();
        so.executeSO(trainer);
        return so.getLoggedIn();
    }

    public void logout(Trainer trainer) throws Exception {
        SOLogout so = new SOLogout();
        so.executeSO(trainer);
    }

    public boolean register(Trainer trainer) throws Exception {
        SORegister so = new SORegister();
        so.executeSO(trainer);
        return so.isSuccess();
    }

    public LinkedList<Trainer> getAllTrainer(Trainer trainer) throws Exception {
        SOGetAllTrainer so = new SOGetAllTrainer();
        so.executeSO(trainer);
        return so.getList();

    }

    public Boolean updateTrainer(Trainer trainer) throws Exception {
        SOUpdateTrainer so = new SOUpdateTrainer();
        so.executeSO(trainer);
        return so.isSuccess();
    }

    //client operations
    public Boolean addClient(Client client) throws Exception {
        SOAddClient so = new SOAddClient();
        so.executeSO(client);
        return so.isSuccess();
    }

    public LinkedList<Client> getAllClient(Client client) throws Exception {
        SOGetAllClient so = new SOGetAllClient();
        so.executeSO(client);
        return so.getList();
    }

    public Boolean updateClient(Client client) throws Exception {
        SOUpdateClient so = new SOUpdateClient();
        so.executeSO(client);
        return so.isSuccess();
    }

    public LinkedList<Client> searchClient(Client criteria) throws Exception {
        SOSearchClient so = new SOSearchClient();
        so.executeSO(criteria);
        return so.getList();
    }

    public Boolean deleteClient(Client client) throws Exception {
        SODeleteClient so = new SODeleteClient();
        so.executeSO(client);
        return so.isSuccess();
    }

    //gym operations
    public Boolean addGym(Gym gym) throws Exception {
        SOAddGym so = new SOAddGym();
        so.executeSO(gym);
        return so.isSuccess();
    }

    public LinkedList<Gym> getAllGym(Gym gym) throws Exception {
        SOGetAllGym so = new SOGetAllGym();
        so.executeSO(gym);
        return so.getList();
    }

    public Boolean updateGym(Gym g) throws Exception {
        SOUpdateGym so = new SOUpdateGym();
        so.executeSO(g);
        return so.isSuccess();
    }

    //activity operations
    public LinkedList<Activity> getAllActivity(Activity a) throws Exception {
        SOGetAllActivity so = new SOGetAllActivity();
        so.executeSO(a);
        return so.getList();
    }

    public Boolean addActivity(Activity a) throws Exception {
        SOAddActivity so = new SOAddActivity();
        so.executeSO(a);
        return so.isSuccess();
    }

    public Boolean updateActivity(Activity a) throws Exception {
        SOUpdateActivity so = new SOUpdateActivity();
        so.executeSO(a);
        return so.isSuccess();
    }

    //workout record operations
    public Boolean addWorkoutRecord(WorkoutRecord wr) throws Exception {
        SOAddWorkoutRecord so = new SOAddWorkoutRecord();
        so.executeSO(wr);
        return so.isSuccess();
    }

    public Boolean updateWorkoutRecord(WorkoutRecord wr) throws Exception {
        SOUpdateWorkoutRecord so = new SOUpdateWorkoutRecord();
        so.executeSO(wr);
        return so.isSuccess();
    }

    public LinkedList<WorkoutRecord> searchWorkoutRecords(WorkoutRecordCriteria c) throws Exception {
        SOSearchWorkoutRecords so = new SOSearchWorkoutRecords();
        so.executeSO(c);
        return so.getList();
    }

    public Boolean deleteWorkoutRecord(WorkoutRecord wr) throws Exception {
        SODeleteWorkoutRecord so = new SODeleteWorkoutRecord();
        so.executeSO(wr);
        return so.isSuccess();
    }

    //sertificate operations
    public Boolean addSertificate(Sertificate s) throws Exception {
        SOAddSertificate so = new SOAddSertificate();
        so.executeSO(s);
        return so.isSuccess();
    }

    public Boolean updateSertificate(Sertificate s) throws Exception {
        SOUpdateSertificate so = new SOUpdateSertificate();
        so.executeSO(s);
        return so.isSuccess();
    }

    public LinkedList<Sertificate> getAllSertificate() throws Exception {
        SOGetAllSertificate so = new SOGetAllSertificate();
        so.executeSO(new Sertificate());
        return so.getList();
    }

    //trainer sertificate operations
    public Boolean addTrainerSertificate(TrainerSertificate ts) throws Exception {
        SOAddTrainerSertificate so = new SOAddTrainerSertificate();
        so.executeSO(ts);
        return so.isSuccess();
    }

    public LinkedList<TrainerSertificate> getTrainerSertificates(Trainer t) throws Exception {
        SOGetTrainerSertificates so = new SOGetTrainerSertificates();
        so.executeSO(t);
        return so.getList();
    }

}
