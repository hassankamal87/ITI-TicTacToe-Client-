/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.utility.GameLevel;
import tictactoe.utility.GameMode;
import tictactoe.utility.MatchStatus;
import tictactoe.utility.PlayerSympol;

/**
 * FXML Controller class
 *
 * @author WIN 10
 */
public class ResultScreenController implements Initializable {

    @FXML
    private MediaView winnerScrn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button restartBtn;

    private File file;
    private Media media;
    private MediaPlayer mediaplayer;
    
    private MatchStatus matchStatus;
    private GameMode gameMode;
    private int leftSideScore;
    private int rightSideScore;
    private int winnerSide;
    private PlayerSympol playerSympol;
    private GameLevel gameLevel;

    public ResultScreenController() {
    }

    public ResultScreenController(MatchStatus result) {
        matchStatus = result;
    }
    
    public ResultScreenController(GameMode mode,MatchStatus result,int left,int right,int winner) {
    }
    
    public ResultScreenController(GameMode mode,MatchStatus result,PlayerSympol sympol,GameLevel level,int left,int right,int winner) {
        
        gameMode = mode;
        matchStatus = result;
        playerSympol = sympol;
        leftSideScore = left;
        rightSideScore = right;
        winnerSide = winner;
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(null!=matchStatus) switch (matchStatus) {
            case WIN:
                media = new Media(getClass().getResource("/tictactoe/assets/win.mp4").toString());
                break;
            case LOSE:
                media = new Media(getClass().getResource("/tictactoe/assets/lose.mp4").toString());
                break;
            case DRAW:
                media = new Media(getClass().getResource("/tictactoe/assets/lose.mp4").toString());
                break;
            default:
                break;
        }
        mediaplayer = new MediaPlayer(media);
        winnerScrn.setMediaPlayer(mediaplayer);
        mediaplayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaplayer.stop();
                winnerScrn.setVisible(false);
            }
        });
        mediaplayer.play();
    }

    @FXML
    private void homeHandler(ActionEvent event) throws IOException {
        
        mediaplayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 610, 410);
        Stage stage = (Stage) homeBtn.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void restartHandler(ActionEvent event) throws IOException {
        
            mediaplayer.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/GameScreen.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == GameScreenController.class) {
                    return new GameScreenController(GameMode.multiply);
                } else {
                    try {
                        return clazz.newInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

}
