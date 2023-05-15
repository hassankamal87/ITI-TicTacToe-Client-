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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.Connection;
import tictactoe.utility.GameMode;
import tictactoe.utility.PlayerSympol;

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

    Connection connection;
    ArrayList<Player> players = null;
    String email = null;

    public MainScreenUiController() {

    }
    public MainScreenUiController(String email) {
        this.email = email;
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
        connection = Connection.getInstance();
        if(email!=null&&players != null){
            goToOnlineList();
        }
        else if (connection.isConnected()&&email == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/SignInScreen.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    if (clazz == SignInScreenController.class) {

                        return new SignInScreenController();
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
            Stage stage = (Stage) localBtn.getScene().getWindow();
            stage.setScene(scene);
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/ServerIP.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 610, 410);
            Stage stage = (Stage) computerBtn.getScene().getWindow();
            stage.setScene(scene);
        }
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
    @FXML
    private void historyHandler(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameHistoryScreen.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == GameHistoryScreenController.class) {
                    return new GameHistoryScreenController();
                } else {
                    try {
                        return clazz.newInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        Parent historyRoot = loader.load();
        Scene historyScene = new Scene(historyRoot, 610, 410);
        Stage primaryStage = (Stage) onlineBtn.getScene().getWindow();
        primaryStage.setScene(historyScene);

    }
    
    
    private void goToOnlineList() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/OnlineFriendListScreen.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == OnlineFriendListScreenController.class) {
                    return new OnlineFriendListScreenController(email);
                } else {
                    try {
                        return clazz.newInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        Parent onlineFriendListRoot = loader.load();
        Scene onlineFriendListScene = new Scene(onlineFriendListRoot, 610, 410);
        Stage primaryStage = (Stage) onlineBtn.getScene().getWindow();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                primaryStage.setScene(onlineFriendListScene);
            }
        });

        primaryStage.setOnCloseRequest(event -> {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException ex) {
                    Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
