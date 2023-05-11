/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import static java.lang.Thread.sleep;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    Connection connection;
    String resultString;
    JSONObject signinObject = new JSONObject();
    String ip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public SignInScreenController() {
        connection = Connection.getInstance(ip);
    }

    public SignInScreenController(String ip) {
        this.ip = ip;
        connection = Connection.getInstance(ip);
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
                                try {
                                    alert.setContentText("Sorry, Server Is Closed Right Now, Please Try Again Later");
                                    alert.showAndWait();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
                                    Parent root = loader.load();
                                    Scene scene = new Scene(root, 610, 410);
                                    Stage stage = (Stage) backBtn.getScene().getWindow();
                                    stage.setScene(scene);
                                } catch (IOException ex1) {
                                    Logger.getLogger(SignInScreenController.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        });
                    }
                }
            }
        }.start();
    }

    @FXML
    private void signInHandler(ActionEvent event) {
        resultString = validation();
        if (resultString.equals("")) {
            signinObject.put(JsonObjectHelper.HEADER, JsonObjectHelper.LOGIN);
            signinObject.put(JsonObjectHelper.EMAIL, email);
            signinObject.put(JsonObjectHelper.PASSWORD, password);

            new Thread() {
                public void run() {
                    if (connection.getPrintStream() != null) {
                        connection.getPrintStream().println(signinObject);
                        JSONObject jsonRespone;
                        try {

                            jsonRespone = (JSONObject) new JSONParser().parse(connection.getBufferReader().readLine());

                            switch (jsonRespone.get(JsonObjectHelper.SIGNIN_STATUS).toString()) {
                                case JsonObjectHelper.SIGNUP_SUCCESS:
                                    signIpSuccess();
                                    break;
                                case JsonObjectHelper.SIGNIN_FAIL:
                                    signInFail();
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
    private void goToSignup(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/SignUPScreen.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == SignUPScreenController.class) {
                    return new SignUPScreenController(ip);
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

    private void signIpSuccess() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/OnlineFriendListScreen.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root, 610, 410);
                    Stage stage = (Stage) signInBtn.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setOnCloseRequest(event -> {
                        if (connection != null) {
                            try {
                                connection.close();
                            } catch (IOException ex) {
                                Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(SignUPScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void signInFail() {
        alert.setContentText("Wrong Email or Password");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                alert.show();
            }
        });
    }

}
