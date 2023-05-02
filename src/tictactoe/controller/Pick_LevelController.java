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
import javafx.util.Callback;
import tictactoe.utility.GameLevel;
import tictactoe.utility.GameMode;

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
    
    
    
    
    @FXML
    private void EasyHandler(MouseEvent event) throws IOException {
        System.out.println("easy");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/PickYourSideScreen.fxml"));
        
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == PickYourSideScreenController.class) {
                    return new PickYourSideScreenController(GameLevel.EASY);
                } else {
                    try {
                        return clazz.newInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        Parent pickLevelRoot = loader.load();

        Scene pickSideScene = new Scene(pickLevelRoot, 610, 410);
        Stage primaStage = (Stage) Easy.getScene().getWindow();
        primaStage.setScene(pickSideScene);
    }
    
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

    
    

    @FXML
    private void MeduimHandler(ActionEvent event) {
    }

    @FXML
    private void HardHandler(ActionEvent event) {
    }

    @FXML
    private void BackHandler(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       Stage stage = (Stage) Back.getScene().getWindow();
        stage.setScene(scene);
    }

    
    
}
