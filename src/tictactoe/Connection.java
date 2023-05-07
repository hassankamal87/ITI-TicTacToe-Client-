/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
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
public class Connection implements Runnable {

    private static Connection instance = null;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;
    private Thread clientThread;

    private Connection() {
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public void startConnection() throws SocketException {
        try {
            server = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            clientThread = new Thread(this);
            clientThread.start();
        } catch (SocketException ex) {
            throw new SocketException();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeConnection() {
        try {
            if (server != null) {
                clientThread.stop();
                server.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isConnected(){
        return server != null;
    }
    
    public PrintStream getPrintStream(){
        return ps;
    }

    @Override
    public void run() {
        while (true) {

            try {

                System.out.println(dis.readLine());

            } catch (SocketException ex) {
                try {
                    dis.close();
                    ps.close();
                    server.close();
                    System.out.println("server closed from thread");
                    break;
                } catch (IOException exception) {
                    Logger.getLogger(Connection.class
                            .getName()).log(Level.SEVERE, null, exception);

                }
            } catch (IOException ex) {
                Logger.getLogger(Connection.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
