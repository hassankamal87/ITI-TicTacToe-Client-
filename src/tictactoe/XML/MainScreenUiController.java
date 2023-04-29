/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.XML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class MainScreenUiController implements Initializable {

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

//        computerBtn.addEventHandler(ActionEvent.ACTION,
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event
//            ) {
//                System.out.println("adsgasdgasdg");
//            }
//        }
//        );
    }

    @FXML
    private void playWithComputer(ActionEvent event) throws IOException {
        PickYourSideScreenController.setMode(PickYourSideScreenController.COMPUTER_MODE);
        gotoPickSide();
    }

    @FXML
    private void playlocal(ActionEvent event) throws IOException {
        PickYourSideScreenController.setMode(PickYourSideScreenController.LOCAL_MODE);
        gotoPickSide();
    }

    private void gotoPickSide() throws IOException {
        System.out.println(PickYourSideScreenController.getMode()+"");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PickYourSideScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) computerBtn.getScene().getWindow();
        stage.setScene(scene);
    }

}
