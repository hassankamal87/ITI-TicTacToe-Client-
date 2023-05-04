/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * 
 * FXML Controller class
 *
 * @author AB
 */
public class SplashScreenController implements Initializable {

    @FXML
    private ImageView backGroundImg;
    @FXML
    private ImageView oImg;
    @FXML
    private ImageView xImg;
    @FXML
    private Label comeLabel;
    @FXML
    private Label welLabel;
    @FXML
    private Label loadingLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        translate(oImg,-460);
        translate(xImg,460);
        
        rotate(oImg);
        rotate(xImg);
        
        fade(loadingLabel);
        
    }    
    
    private void translate(Node node, double x){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(node);
        translate.setDuration(Duration.millis(3000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(x);
        translate.setByY(100);
        translate.setAutoReverse(true);
        translate.play();
    }
    
    private void rotate(Node node){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(node);
        rotate.setDuration(Duration.millis(3000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();
    }
    
    private void fade(Node node){
        FadeTransition fade = new FadeTransition();
        fade.setNode(node);
        fade.setDuration(Duration.millis(2000));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }
    
    
    
}
