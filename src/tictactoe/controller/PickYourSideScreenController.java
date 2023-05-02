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
import javafx.util.Callback;
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
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == GameScreenController.class) {
                    return new GameScreenController(PlayerSympol.X,level);
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
        Stage primaryStage = (Stage) oImg.getScene().getWindow();
        primaryStage.setScene(gameScene);
    }

    @FXML
    private void setOAndNavigate(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == GameScreenController.class) {
                    return new GameScreenController(PlayerSympol.O,level);
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
        Stage primaryStage = (Stage) oImg.getScene().getWindow();
        primaryStage.setScene(gameScene);
    }
    

    
}
