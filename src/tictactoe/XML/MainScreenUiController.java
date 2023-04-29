/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.XML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

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
    private void playWithComputer(ActionEvent event) {
      
    }
    
}
