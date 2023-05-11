/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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
    
    public OnlineItemHolderController(Player player){
        this.name = player.getName();
        this.playerEmail = player.getEmail();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerName.setText(name);
        email.setText(playerEmail);
    }    

    @FXML
    private void inviteFriend(ActionEvent event) {
        //send Request
        System.out.println("invite clicked");
    }
}
