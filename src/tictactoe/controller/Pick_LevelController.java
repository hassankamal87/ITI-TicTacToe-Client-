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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shima
 */
public class Pick_LevelController implements Initializable {

   
private boolean animationPlusFlag = true;
    

    @FXML
    private Button Easy;
    @FXML
    private Button Medium;
    @FXML
    private Button Hard;
    @FXML
    private Button Back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

  private void EasyHandler(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,610,410);
            Stage stage = (Stage) Easy.getScene().getWindow();
            stage.setScene(scene);
    }       
    
 /*   
    
private  void EasyMode(ActionEvent event) {
        GameScreenController.setMode(GameScreenController.Easy_MODE);
       gotoGameScreen();
    }
private  void MediumMode(ActionEvent event) {
        GameScreenController.setMode(GameScreenController.Medium_MODE);
       gotoGameScreen();
    }
   
private  void HardMode(ActionEvent event) {
        GameScreenController.setMode(GameScreenController.Hard_MODE);
       gotoGameScreen();
    }*/
  /*
    private void gotoGameScreen (MouseEvent event) throws IOException {

            System.out.println(GameScreenController.getMode() + "");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Easy.getScene().getWindow();
            stage.setScene(scene);
            /*
        } catch (IOException ex) {
            Logger.getLogger(Pick_LevelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    private void checkMouseEntered(MouseEvent event) {
        
    }

    
    private void BackHandller(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/PickYourSideScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,610,410);
       Stage stage = (Stage) Back.getScene().getWindow();
        stage.setScene(scene);
    }
    
}
