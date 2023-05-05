/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tictactoe.ClientNetwork;

/**
 * FXML Controller class
 *
 * @author WIN 10
 */
public class SignUPScreenController implements Initializable {

    @FXML
    private Button signUpBtnUp;
    @FXML
    private ImageView backBtn;
    @FXML
    private PasswordField passwordTextFieldUp;
    @FXML
    private TextField userNameTextFieldUp;
    @FXML
    private Button mainManuBtn;
    @FXML
    private PasswordField passwordConTextFieldUp;
    
    ClientNetwork clientNetwork;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientNetwork = new ClientNetwork();
    }    

    @FXML
    private void signUpHandler(ActionEvent event) {
        /*connect to database*/
        
    }

    @FXML
    private void backHandler(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,610,410);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
    }

    @FXML
    private void mainManuHandler(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
    }
}
