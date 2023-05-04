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

    public void startConnection() {
        try {
            server = new Socket("172.17.64.1", 5005);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            clientThread = new Thread(this);
            clientThread.start();
        } catch (SocketException ex) {
            System.out.println("serverClosed");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void closeConnection() {
        try {
            clientThread.stop();
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {

            try {

                System.out.println(dis.readLine());

            } catch (SocketException ex) {
                try {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    dis.close();
                    ps.close();
                    server.close();
                    System.out.println("server closed");
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
