/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tictactoe.Connection;
import tictactoe.utility.JsonObjectHelper;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class OnlineFriendListScreenController implements Initializable {

    @FXML
    private ListView<?> listItemHolder;
    ObservableList<Player> players = FXCollections.observableArrayList();
    String myEmail;
    JSONObject onlinePlayer = new JSONObject();

    public OnlineFriendListScreenController(String myEmail) {
        this.myEmail = myEmail;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        players = getOnLineScreenFromServer();

        renderList(players);

    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    if (clazz == MainScreenUiController.class) {
                        return new MainScreenUiController(myEmail);
                    } else {
                        try {
                            return clazz.newInstance();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
            Parent root = loader.load();
            Scene scene = new Scene(root, 610, 410);
            Stage stage = (Stage) listItemHolder.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Player> getOnLineScreenFromServer() {

        Thread onlineListThread = new Thread() {
            public void run() {
                players.clear();
                while (true) {
                    System.out.println("now");
                    JSONObject askOnlineList = new JSONObject();
                    askOnlineList.put(JsonObjectHelper.HEADER, "onlineList");
                    Connection.getInstance().getPrintStream().println(askOnlineList);
                    try {
                        onlinePlayer = (JSONObject) new JSONParser().parse(Connection.getInstance().getBufferReader().readLine());
                    } catch (IOException ex) {
                        Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (onlinePlayer != null) {
                        if (onlinePlayer.get(JsonObjectHelper.HEADER).toString().equals("list")) {
                            Player player = new Player();
                            player.setEmail(onlinePlayer.get(JsonObjectHelper.EMAIL).toString());
                            player.setName(onlinePlayer.get(JsonObjectHelper.NAME).toString());
                            if (!player.getEmail().equals(myEmail)) {
                                players.add(player);
                            }
                        } else if (onlinePlayer.get(JsonObjectHelper.HEADER).toString().equals("end")) {
                            this.stop();
                            break;
                        }
                    }

                }
            }
        };
        onlineListThread.start();
        try {
            // wait for the onlineListThread to complete
            onlineListThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }

    private void renderList(ObservableList<Player> playersList) {
        ArrayList<Node> list = new ArrayList<>();
        for (Player p : playersList) {
            System.out.println("hererere");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/OnlineItemHolder.fxml"));
                loader.setControllerFactory(new Callback<Class<?>, Object>() {
                    @Override
                    public Object call(Class<?> clazz) {
                        if (clazz == OnlineItemHolderController.class) {
                            return new OnlineItemHolderController(p, myEmail);
                        } else {
                            try {
                                return clazz.newInstance();
                            } catch (Exception ex) {
                                System.out.println("here");
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });

                Node node = loader.load();
                list.add(node);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        if (playersList.size() != 0) {
            ObservableList items = FXCollections.observableArrayList(list);
            listItemHolder.setItems(items);
        }

    }

    @FXML
    private void refresh(MouseEvent event) {
        System.out.println("tictactoe.controller.OnlineFriendListScreenController.refresh()");
        new Thread() {
            public void run() {
                players = getOnLineScreenFromServer();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        listItemHolder.getItems().clear();
                        renderList(players);
                    }
                });

            }
        }.start();

//        listItemHolder.refresh();
    }

}
