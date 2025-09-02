/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver {
    private final Socket socket;
    ObjectInputStream in;

    public Receiver(Socket socket) throws Exception {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
    }
    
    public  Object receive() throws Exception{
        try {
            
            return in.readObject();
        } catch (IOException ex) {
             ex.printStackTrace();
             throw new Exception("Greska kod citanja objekta: " + ex.getMessage());
        }
       
    }
}
