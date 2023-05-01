/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WIN 10
 */
public class ServerMainScreenController implements Initializable {

    @FXML
    private Button playerStatusBtn;
    @FXML
    private ToggleButton serverActivationBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void playerStatusHandler(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/PlayerGraphScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,610,410);
            Stage stage = (Stage) playerStatusBtn.getScene().getWindow();
            stage.setScene(scene);
    }

    @FXML
    private void serverActivationHandler(ActionEvent event) {
    }
    
}
