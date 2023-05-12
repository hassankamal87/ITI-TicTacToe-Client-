/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
public class OnlineItemHolderController implements Initializable {

    @FXML
    private Label playerName;
    @FXML
    private Label email;
    
    String name;
    String playerEmail;
    String myEmail;
    JSONObject sendObject = new JSONObject();
    Connection connection;
    
    
    
    public OnlineItemHolderController(Player player, String myEmail){
        this.name = player.getName();
        this.playerEmail = player.getEmail();
        this.myEmail = myEmail;
        System.out.println(playerEmail+"OnlineItemHolderController");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerName.setText(name);
        email.setText(playerEmail);
        connection = Connection.getInstance();
    }    

    @FXML
    private void inviteFriend(ActionEvent event) {
        //send Request
        System.out.println("invite clicked");
        
        
        sendObject.clear();
            sendObject.put(JsonObjectHelper.HEADER, JsonObjectHelper.SEND);
            sendObject.put(JsonObjectHelper.SENDER, myEmail);
            sendObject.put(JsonObjectHelper.RECEIVER, email.getText().toString());
            System.out.println(sendObject.toJSONString()+"inviteFriend");
            System.out.println(sendObject.get(JsonObjectHelper.SENDER)+"inviteFriend");

            new Thread() {
                public void run() {
                    if (connection.getPrintStream() != null) {
                        System.out.println(sendObject.toJSONString());
                        connection.getPrintStream().println(sendObject);
                        System.out.println("donnee");
                        
                    } else {
                        System.out.println("error in send invitation");
                    }

                }
            }.start();
    }
}
