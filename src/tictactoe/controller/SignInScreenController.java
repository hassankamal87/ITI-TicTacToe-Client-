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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import tictactoe.Connection;
import tictactoe.utility.JsonObjectHelper;

/**
 * FXML Controller class
 *
 * @author WIN 10
 */
public class SignInScreenController implements Initializable {

    @FXML
    private Button signInBtn;
    @FXML
    private ImageView backBtn;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordTextField;

    Alert alert = new Alert(Alert.AlertType.ERROR);
    String email;
    String password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signInHandler(ActionEvent event) {
        Connection connection = Connection.getInstance();
        String resultString = validation();
        if (resultString.equals("")) {
            JSONObject signinObject = new JSONObject();
            signinObject.put(JsonObjectHelper.HEADER, JsonObjectHelper.LOGIN);
            signinObject.put(JsonObjectHelper.EMAIL, email);
            signinObject.put(JsonObjectHelper.PASSWORD, password);
            new Thread() {
                @Override
                public void run() {
                    if (!connection.isConnected()) {
                        try {
                            connection.startConnection();
                        } catch (SocketException ex) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    alert.setContentText("Sorry, Server Is Closed Right Now");
                                    alert.showAndWait();
                                }
                            });

                        }
                    }
                    //to avoid exception 
                    if (connection.isConnected()) {
                        connection.getPrintStream().println(signinObject);
                    }
                }
            }.start();
        } else {
            alert.setContentText(resultString);
            alert.show();
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

    @FXML
    private void goToSignup(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/SignUPScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 610, 410);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(scene);
    }

    private String validation() {
        String errorMessage = "";
        email = userNameTextField.getText().trim();
        password = passwordTextField.getText().trim();

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "All fields are required";
        } else if (!matcher.matches()) {
            errorMessage = "Enter Valid Email Please";
        } else if (password.length() < 8) {
            errorMessage = "Password can't be less than 8 symbols";
        }
        return errorMessage;
    }

}
