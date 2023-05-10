/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tictactoe.Connection;
import tictactoe.utility.GameMode;
import tictactoe.utility.JsonObjectHelper;

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
    private Button mainManuBtn;
    @FXML
    private PasswordField passwordConTextFieldUp;
    @FXML
    private TextField nameTextFieldUp;
    @FXML
    private TextField emailTextFieldUp;

    String email;
    String name;
    String password;
    String rePassword;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    BufferedReader br;
    Connection connection;
    JSONObject responseObject = new JSONObject();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public SignUPScreenController(String ip) {
        connection = Connection.getInstance(ip);
        new Thread() {
            public void run() {
                try {
                    connection.startConnection();
                } catch (SocketException ex) {

                }
            }
        }.start();
    }

    /**
     * Initializes the controller class.
     */
    @FXML

    private void signUpHandler(ActionEvent event) {
        /*connect to database*/
        String resultString = validation();
        if (resultString == "") {
            JSONObject signupObject = new JSONObject();
            signupObject.put(JsonObjectHelper.HEADER, JsonObjectHelper.SIGNUP);
            signupObject.put(JsonObjectHelper.EMAIL, email);
            signupObject.put(JsonObjectHelper.NAME, name);
            signupObject.put(JsonObjectHelper.PASSWORD, password);

            new Thread() {
                public void run() {
                    if (connection.getPrintStream() != null) {
                        connection.getPrintStream().println(signupObject);
                        JSONObject jsonRespone;
                        try {

                            jsonRespone = (JSONObject) new JSONParser().parse(connection.getBufferReader().readLine());

                            switch (jsonRespone.get(JsonObjectHelper.SIGNUP_STATUS).toString()) {
                                case JsonObjectHelper.SIGNUP_SUCCESS:
                                    //go to signin screen
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                                alert.setContentText("Signup Successfully");
                                                alert.showAndWait();
                                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/SignInScreen.fxml"));
                                                Parent root = loader.load();
                                                Scene scene = new Scene(root, 610, 410);
                                                Stage stage = (Stage) signUpBtnUp.getScene().getWindow();
                                                stage.setScene(scene);
                                            } catch (IOException ex) {
                                                Logger.getLogger(SignUPScreenController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });

                                    break;
                                case JsonObjectHelper.SIGNUP_FAIL_DUPLICATE:
                                    //go to game screen
                                    alert.setContentText("Email Already Exists");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            alert.show();
                                        }
                                    });
                                    break;
                            }

                        } catch (IOException ex) {
                            Logger.getLogger(SignUPScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(SignUPScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                alert.setContentText("Sorry, Server Is Closed Right Now");
                                alert.show();
                            }
                        });
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
    private void mainManuHandler(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(scene);
    }

    private String validation() {
        String errorMessage = "";
        email = emailTextFieldUp.getText().trim();
        name = nameTextFieldUp.getText().trim();
        password = passwordTextFieldUp.getText().trim();
        rePassword = passwordConTextFieldUp.getText().trim();

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            errorMessage = "All fields are required";
        } else if (!matcher.matches()) {
            errorMessage = "Enter Valid Email Please";
        } else if (password.length() < 8) {
            errorMessage = "Password can't be less than 8 symbols";
        } else if (password.length() > 12) {
            errorMessage = "Password can't be more 12 symbols";
        } else if (!password.equals(rePassword)) {
            errorMessage = "Password and confirm password are not identical";
        }
        return errorMessage;
    }
}
