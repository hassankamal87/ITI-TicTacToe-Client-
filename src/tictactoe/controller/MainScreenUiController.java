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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class MainScreenUiController implements Initializable {
    
    private boolean animationPlusFlag = true;

    @FXML
    private Button computerBtn;
    @FXML
    private Button localBtn;
    @FXML
    private Button onlineBtn;
    @FXML
    private Button signInBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private Button historyBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Text UsernameTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
    }

    @FXML
    private void playWithComputer(ActionEvent event) {
        PickYourSideScreenController.setMode(PickYourSideScreenController.COMPUTER_MODE);
        gotoPickSide();
    }

    @FXML
    private void playlocal(ActionEvent event) {
        PickYourSideScreenController.setMode(PickYourSideScreenController.LOCAL_MODE);
        gotoPickSide();
    }

    private void gotoPickSide() {
        try {
            System.out.println(PickYourSideScreenController.getMode() + "");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/PickYourSideScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) computerBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signInHandler(ActionEvent event) {

        try {
            System.out.println(PickYourSideScreenController.getMode() + "");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/SignInScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) signInBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signUpHandler(ActionEvent event) {
        try {
            System.out.println(PickYourSideScreenController.getMode() + "");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/SignUPScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) signUpBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void historyHandler(ActionEvent event) {
    }

    private void translate(Node node, double x) {
        onlineBtn.setDisable(true);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(node);
        translate.setDuration(Duration.millis(100));
        translate.setCycleCount(1);
        translate.setByY(x);
        translate.play();
        onlineBtn.setDisable(false);
    }


    private void checkMouseEntered(MouseEvent event) {
        
    }

    @FXML
    private void checkMousePressed(MouseEvent event) {
        Stage stage = (Stage) onlineBtn.getScene().getWindow();
        if(animationPlusFlag){
             translate(onlineBtn, 70);
             animationPlusFlag = false;
        } 
        else{
            translate(onlineBtn, -70);
            animationPlusFlag = true;
        }
    }
}
