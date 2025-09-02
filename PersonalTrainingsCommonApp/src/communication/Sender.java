/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {
    private final Socket socket;
    private ObjectOutputStream out;

    public Sender(Socket socket)throws Exception{
        this.socket = socket;
        out=new ObjectOutputStream(socket.getOutputStream());
    }
    
    public void send(Object object) throws Exception{
        try {
            out.reset();
            out.writeObject(object);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Greska kod slanja objekta: " + ex.getMessage());
        }
        
    }
    
}
