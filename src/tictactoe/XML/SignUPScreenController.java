/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.XML;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private TextField confirmPasswordTextField;
    @FXML
    private TextField passwordTextFieldUp;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField userNameTextFieldUp;
    @FXML
    private Button mainManuBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signUpHandler(ActionEvent event) {
        /*connect to database*/
        
    }

    @FXML
    private void backHandler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mainManuHandler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreenUi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenUiController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
