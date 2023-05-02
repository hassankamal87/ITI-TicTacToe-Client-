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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file = new File("C:\\Users\\WIN 10\\Downloads\\You Win, Perfect!.mp4");
        media = new Media(file.toURI().toString());
        mediaplayer = new MediaPlayer(media);
        winnerScrn.setMediaPlayer(mediaplayer);
        mediaplayer.play();
    }    

    @FXML
    private void homeHandler(ActionEvent event) throws IOException {
        
            mediaplayer.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,610,410);
            Stage stage = (Stage) homeBtn.getScene().getWindow();
            stage.setScene(scene);
    }

    @FXML
    private void restartHandler(ActionEvent event) {
        
        mediaplayer.stop();
    }
    
}
