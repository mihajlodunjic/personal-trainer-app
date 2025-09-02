/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import communication.Receiver;
import communication.Sender;
import domain.Trainer;
import java.net.Socket;

/**
 *
 * @author pc
 */
public class ClientSession {
    private Socket socket;
    private Trainer loggedIn;
    private Sender sender;
    private Receiver receiver;
    private static ClientSession instance;
    private ClientSession(){
        try {
            socket=new Socket("127.0.0.1",9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ClientSession getInstance(){
        if(instance ==null) instance = new ClientSession();
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Trainer getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Trainer loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }
    
}
