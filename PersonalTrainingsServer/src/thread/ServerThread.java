
package thread;

import database.DatabaseBroker;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    
    
    public ServerThread(){
        try {
            DatabaseBroker.connect();
            serverSocket=new ServerSocket(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ServerSocket getServerSocket(){
        return serverSocket;
    }
    
    public void setServerSocket(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }
    @Override
    public void run() {
        try {
            while(!serverSocket.isClosed()){
                Socket socket=serverSocket.accept();
                System.out.println("Klijent povezan");
                ClientThread ct=new ClientThread(socket);
                ct.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

