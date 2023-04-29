/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class PickYourSideScreenController implements Initializable {
    public static int COMPUTER_MODE = 0;
    public static int LOCAL_MODE = 1;
    private static int gameMode;
    @FXML
    private ImageView xImg;
    @FXML
    private ImageView oImg;
    @FXML
    private ImageView backImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backToMain(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreenUi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backImg.getScene().getWindow();
        stage.setScene(scene);
    }
    
    public static void setMode(int mode){
        gameMode = mode;
    }
    public static int getMode(){
        return gameMode;
    }
    
}
