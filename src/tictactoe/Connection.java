/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed Adel
 */
public class Connection{

    private static Connection instance = null;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;
    private BufferedReader br;
    
    public String ip;

    private Connection() {
        
    }
    
    

    public static synchronized Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
    public void startConnection() throws SocketException {
        try {
            server = new Socket(ip, 5005);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            br = new BufferedReader(new InputStreamReader(dis));
        } catch (SocketException ex) {
            throw new SocketException();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeConnection() {
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close() throws IOException{
        server.close();
    }

    public boolean isConnected() {
        return server != null;
    }

    public PrintStream getPrintStream() {
        return ps;
    }
    
    public DataInputStream getDataInputStream() {
        return dis;
    }
    
    public BufferedReader getBufferReader(){
        return br;
    } 

}
