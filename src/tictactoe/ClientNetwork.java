/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;


/**
 *
 * @author AB
 */
public class ClientNetwork {
    Socket server;
    DataInputStream dis;
    PrintStream ps;
    
    Alert alert;
    
    public ClientNetwork(){
        alert = new Alert(Alert.AlertType.ERROR, "Server is Closed For Now");
        try {
            server = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            ps.println("Hello From the other Side2222");
            System.out.println(dis.readLine());
        } catch (IOException ex) {
            alert.show();
        }
    }
    
    public void closeConnection(){
        try {
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
