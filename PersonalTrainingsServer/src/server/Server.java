/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
//import controller.Controller;
import database.DatabaseBroker;
import domain.Trainer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Server {
    private Sender sender ;
    private Receiver receiver;

    public Server() throws SQLException {
        try {
            DatabaseBroker.connect();
        } catch (Exception e) {
            System.out.println("Server: Nije moguce povezati se sa bazom!");
            throw new RuntimeException(e);
        }
    }
    
    
    public void startServer(){
        try (ServerSocket serverSocket = new ServerSocket(9000)){
            //kreiraj server socket
            System.out.println("Server je pokrenut. cekam klijneta!");
            Socket socket =  serverSocket.accept();
            System.out.println("Server: Klijent se povezao sa serverom...");
            
            sender = new Sender(socket);
            receiver = new Receiver(socket);
            
            while(true){
                try {
                    //citaj zahtev
                    Request request =  (Request) receiver.receive();
                    Response response = new Response();
                    Operation operation = request.getOperation();
                    System.out.println(operation);
                    if(Operation.LOGIN == operation){
                        Trainer user = (Trainer) request.getArgument();
                        System.out.println("Argument iz requesta: "+ user);
                        try {
                            boolean signal=DatabaseBroker.findRowAndReturn(user);
                            System.out.println("signal: "+signal);
                            if(!signal)
                                throw new RuntimeException("Greska pri ucitavanju korisnika");
                            System.out.println("ucitani trener: "+ user);
                            response.setResult(user);
                            
                        } catch (Exception e) {
                            response.setException(e);
                        }
                    }
                    
                    if(Operation.REGISTER==operation){
                        Trainer user = (Trainer) request.getArgument();
//                        boolean signal = Controller.getInstance().register(user);
//                        response.setResult(signal);
                    }
                    
                    
                    //posalji odgovor nazad
                    sender.send(response);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
