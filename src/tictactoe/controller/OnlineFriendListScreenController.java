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
import tictactoe.Connection;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class OnlineFriendListScreenController implements Initializable {
@FXML
    private ListView<?> listItemHolder;
    ArrayList<Player> players;
    String myEmail;

    public OnlineFriendListScreenController(ArrayList<Player> players, String myEmail) {
        this.players = players;
        this.myEmail = myEmail;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        ArrayList<Node> list = new ArrayList();

        for (Player p : players) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/OnlineItemHolder.fxml"));
                loader.setControllerFactory(new Callback<Class<?>, Object>() {
                    @Override
                    public Object call(Class<?> clazz) {
                        if (clazz == OnlineItemHolderController.class) {
                            return new OnlineItemHolderController(p,myEmail);
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
        if (players.size() != 0) {
            ObservableList items = FXCollections.observableArrayList(list);
            listItemHolder.setItems(items);
        }
        /*try {
            // TODO

            ArrayList<Node> list = new ArrayList();
            for (int i = 0; i < 30; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/OnlineItemHolder.fxml"));
                Node node = loader.load();
                list.add(node);
            }
            ObservableList items = FXCollections.observableArrayList(list);
            listItemHolder.setItems(items);
        } catch (IOException ex) {
            Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 610, 410);
            Stage stage = (Stage) listItemHolder.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getOnLineScreenFromServer() {

    }

}
