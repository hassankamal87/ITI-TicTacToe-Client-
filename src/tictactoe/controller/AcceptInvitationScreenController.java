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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author shima
 */
public class AcceptInvitationScreenController implements Initializable {

    @FXML
    private Button StartButton;
    @FXML
    private CheckBox CheckBox;
    @FXML
    private Text WaitingText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void StartGameHandler(ActionEvent event) {
    }

    @FXML
    private void CheckRecordGameHandler(ActionEvent event) {
    }
    
}
