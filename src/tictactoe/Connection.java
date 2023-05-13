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
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tictactoe.controller.GameScreenController;
import tictactoe.utility.GameMode;
import tictactoe.utility.JsonObjectHelper;

/**
 *
 * @author Mohamed Adel
 */
public class Connection {

    private static Connection instance = null;
    private Socket server = null;
    private DataInputStream dis;
    private PrintStream ps;
    private BufferedReader br;
    Thread clientThread;
    JSONObject serverJson;
    JSONObject clientJson;
    Stage primaStage;

    public String ip;

    Alert alert = new Alert(Alert.AlertType.NONE);
    Alert refuseAlert = new Alert(Alert.AlertType.NONE);
    Alert acceptAlert = new Alert(Alert.AlertType.NONE);
    ButtonType acceptBtn = new ButtonType("ACCEPT");
    ButtonType rejectButton = new ButtonType("REJECT");
    ButtonType okBtn = new ButtonType("ok");
    ButtonType startBtn = new ButtonType("start Game");

    private Connection() {

    }

    public static synchronized Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }

        return instance;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPrimaryStage(Stage primaryStage) {
        alert.getDialogPane().getButtonTypes().addAll(acceptBtn, rejectButton);
        refuseAlert.getDialogPane().getButtonTypes().addAll(okBtn);
        acceptAlert.getDialogPane().getButtonTypes().addAll(startBtn);
        this.primaStage = primaryStage;
    }

    public boolean startConnection() throws IOException {
        try {
            server = new Socket(ip, 5005);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            br = new BufferedReader(new InputStreamReader(dis));
            return true;
        } catch (SocketException ex) {
            new MyAlert(Alert.AlertType.ERROR, "please enter valid IP").show();
            return false;
        } catch (IOException ex) {
            new MyAlert(Alert.AlertType.ERROR, "please enter valid IP").show();
            return false;
        }

    }

    public void closeConnection() {
        try {
            if (server != null) {
                server.close();
                clientThread.stop();
            }
        } catch (IOException ex) {
            System.out.println("Connection class in line 74");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() throws IOException {
        server.close();
    }

    public boolean isConnected() {
        if (server == null) {
            return false;
        } else {
            return true;
        }
    }

    public PrintStream getPrintStream() {
        return ps;
    }

    public DataInputStream getDataInputStream() {
        return dis;
    }

    public BufferedReader getBufferReader() {
        return br;
    }

    public JSONObject readMessage() {
        JSONObject response = null;
        try {
            response = (JSONObject) new JSONParser().parse(br.readLine());
        } catch (IOException ex) {
            try {
                closeStreams();
            } catch (IOException ex1) {
                System.out.println("not closed in 129");
            }

        } catch (ParseException ex) {
            System.out.println("connection line 133");

        } catch (NullPointerException e) {
            System.out.println("connection handler 136");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new MyAlert(Alert.AlertType.WARNING, " server is Down").show();
                }
            });
            System.out.println("connection line 144");
            try {
                closeStreams();
            } catch (IOException ex1) {

            }
        }
        System.out.println("line 141 connetion " + response);
        return response;
    }

    private void closeStreams() throws IOException {
        System.out.println("streams closed done");
        dis.close();
        ps.close();
        br.close();
    }

    public void startReceiveInvitation() {
        System.out.println("startReceiveInvitation");
        if (clientThread == null) {
            clientThread = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            serverJson = readMessage();
                        } catch (NullPointerException ex) {
                            System.out.println("connection line 66");
                            break;
                        }
                        if (serverJson != null) {
                            String header = (String) serverJson.get(JsonObjectHelper.HEADER);
                            System.out.println(header);
                            switch (header) {
                                case JsonObjectHelper.INVITATION:
                                    //recieve invite server logic
                                    alert.setContentText(serverJson.get(JsonObjectHelper.SENDER).toString() + " Wants to play with you");

                                    alert.setTitle("Invitation");

                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            Optional<ButtonType> result = alert.showAndWait();
                                            clientJson = new JSONObject();
                                            if (result.isPresent() && result.get() == acceptBtn) {
                                                goToOnlineGameScreen(serverJson.get(JsonObjectHelper.SENDER).toString(),2);
                                                clientJson.put("header", "accept");
                                                clientJson.put("myEmail",serverJson.get(JsonObjectHelper.RECEIVER));
                                                clientJson.put(JsonObjectHelper.EMAIL, serverJson.get(JsonObjectHelper.SENDER).toString());
                                                ps.println(clientJson);
                                            } else if (result.isPresent() && result.get() == rejectButton) {
                                                clientJson.put(JsonObjectHelper.HEADER, JsonObjectHelper.REFUSE);
                                                clientJson.put("myEmail",serverJson.get(JsonObjectHelper.RECEIVER));
                                                clientJson.put(JsonObjectHelper.EMAIL, serverJson.get(JsonObjectHelper.SENDER).toString());
                                                ps.println(clientJson);
                                            }
                                        }
                                    });
                                    break;
                                case "refuse":
                                    refuseAlert.setContentText(" " + serverJson.get("myEmail") + " can't play right now");
                                    refuseAlert.setTitle("SORRY");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            Optional<ButtonType> result = refuseAlert.showAndWait();
                                            clientJson = new JSONObject();
                                            if (result.isPresent() && result.get() == okBtn) {

                                            }
                                        }
                                    });
                                    break;
                                case "accept":
                                    acceptAlert.setContentText(" " + serverJson.get("myEmail") + " Accepted your invitation ");
                                    acceptAlert.setTitle("ACCEPTED");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            Optional<ButtonType> result = acceptAlert.showAndWait();
                                            clientJson = new JSONObject();
                                            if (result.isPresent() && result.get() == startBtn) {
                                                goToOnlineGameScreen(serverJson.get("myEmail").toString(),1);
                                            }
                                        }
                                    });
                                    break;

                            }
                        } else {

                            break;
                        }
                    }
                }
            };
        }

        clientThread.start();
    }

    private void goToOnlineGameScreen(String em, int pos) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    if (clazz == GameScreenController.class) {
                        return new GameScreenController(GameMode.online, em, pos);
                    } else {
                        try {
                            return clazz.newInstance();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
            Parent gameRoot = loader.load();
            Scene gameScene = new Scene(gameRoot, 610, 410);

            primaStage.setScene(gameScene);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
