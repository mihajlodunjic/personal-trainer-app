/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.sql.SQLException;
import server.Server;

/**
 *
 * @author pc
 */
public class MainServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Server server = new Server();
        server.startServer();
    }
    
}
