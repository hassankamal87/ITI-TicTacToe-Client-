/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.utility.GameMode;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class HistoryItemHolderController implements Initializable {

    @FXML
    private Label playerName;
    String nameWithDate;

    public HistoryItemHolderController(String playerName) {
       this.nameWithDate = playerName;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playerName.setText(nameWithDate);
        
    }    

    @FXML
    private void replayGame(ActionEvent event) {
       try{
            String rr = readDataFromRecordingFolder(nameWithDate);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    if (clazz == GameScreenController.class) {
                        return new GameScreenController(GameMode.record,rr);
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
            Stage primaryStage = (Stage) playerName.getScene().getWindow();
            primaryStage.setScene(gameScene);
        } catch (IOException ex) {
            Logger.getLogger(GameHistoryScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String readDataFromRecordingFolder(String path) {
        String[] movesList;
        String txt = "";
        File file = new File(".\\src\\tictactoe\\recordingFile\\"+path);
        if (file != null) {
            try {
                FileInputStream fis = new FileInputStream(file);
                int size = fis.available();
                byte[] b = new byte[size];
                fis.read(b);
                txt = new String(b);
                fis.close();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        movesList = txt.split("\\.");
        return txt;
    }
    
}
