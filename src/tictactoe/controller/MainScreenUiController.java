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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.utility.GameMode;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class MainScreenUiController implements Initializable {

    //private boolean animationPlusFlag = true;

    @FXML
    private Button computerBtn;
    @FXML
    private Button localBtn;
    @FXML
    private Button historyBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button onlineBtn;
    @FXML
    private Text UsernameTxt;

    boolean isSignedin = false;

    public MainScreenUiController() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void playWithComputer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/Pick_Level.fxml"));
        Parent pickLevelRoot = loader.load();
        Scene pickLevelScene = new Scene(pickLevelRoot, 610, 410);
        Stage primaStage = (Stage) computerBtn.getScene().getWindow();
        primaStage.setScene(pickLevelScene);
    }

    @FXML
    private void playlocal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == GameScreenController.class) {
                    return new GameScreenController(GameMode.multiplayer);
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

        Stage primaStage = (Stage) computerBtn.getScene().getWindow();
        primaStage.setScene(gameScene);
    }

    @FXML
    private void checkOnline(MouseEvent event) throws IOException {
        if (isSignedin) {
            //user is singed in and can play online
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/SignInScreen.fxml"));
            Parent pickLevelRoot = loader.load();
            Scene pickLevelScene = new Scene(pickLevelRoot, 610, 410);
            Stage primaStage = (Stage) computerBtn.getScene().getWindow();
            primaStage.setScene(pickLevelScene);
        }
    }


    @FXML
    private void historyHandler(ActionEvent event) {
    }

//    private void translate(Node node, double x) {
//        onlineBtn.setDisable(true);
//        TranslateTransition translate = new TranslateTransition();
//        translate.setNode(node);
//        translate.setDuration(Duration.millis(100));
//        translate.setCycleCount(1);
//        translate.setByY(x);
//        translate.play();
//        onlineBtn.setDisable(false);
//    }
}
