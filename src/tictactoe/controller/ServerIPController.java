/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.net.SocketException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.Connection;
import tictactoe.MyAlert;

/**
 * FXML Controller class
 *
 * @author WIN 10
 */
public class ServerIPController implements Initializable {

    @FXML
    private TextField ipTextField;
    @FXML
    private Button connectBtn;
    @FXML
    private ImageView backBtn;

    Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void connectHandler(ActionEvent event) throws IOException  {

            Stage stage = (Stage) connectBtn.getScene().getWindow();
            connection = Connection.getInstance();
            connection.setIp(ipTextField.getText());
            connection.setPrimaryStage(stage);
            if (connection.startConnection()) {
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
                stage.setScene(scene);
            }else{
                new MyAlert(Alert.AlertType.INFORMATION, "please enter valid ip");
            }
        
    }

    @FXML
    private void backHandler(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 610, 410);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(scene);
    }

}
