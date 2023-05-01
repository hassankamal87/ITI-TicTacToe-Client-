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
import tictactoe.utility.GameLevel;
import tictactoe.utility.GameMode;
import tictactoe.utility.PlayerSympol;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class PickYourSideScreenController implements Initializable {

    @FXML
    private ImageView xImg;
    @FXML
    private ImageView oImg;
    @FXML
    private ImageView backImg;
    
    private PlayerSympol symbol;
    private GameLevel level;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //I didn't create an empty constructor because it gonna be used only in computer mode.
    public PickYourSideScreenController(GameLevel level) {
        this.symbol = symbol;
        this.level = level;
    }
 
    @FXML
    private void backToMain(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) backImg.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void setXAndNavigate(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
        Parent gameRoot = loader.load();
        GameScreenController gameController = new GameScreenController(GameMode.computer , PlayerSympol.X);
        loader.setController(gameController);
        Scene gameScene = new Scene(gameRoot, 610, 410);
        Stage stage = (Stage) xImg.getScene().getWindow();
        stage.setScene(gameScene);
    }

    @FXML
    private void setOAndNavigate(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
        Parent gameRoot = loader.load();
        GameScreenController gameController = new GameScreenController(GameMode.computer , PlayerSympol.O);
        loader.setController(gameController);
        Scene gameScene = new Scene(gameRoot, 610, 410);
        Stage stage = (Stage) xImg.getScene().getWindow();
        stage.setScene(gameScene);
    }
    

    
}
