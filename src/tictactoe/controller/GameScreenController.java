
package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AB
 */
public class GameScreenController implements Initializable {

    @FXML
    private ImageView backGroundImg;
    @FXML
    private Text gameKindTextView;
    @FXML
    private Button quitBtn;
    @FXML
    private ImageView gridImg;
    @FXML
    private Button b00;
    @FXML
    private Button b01;
    @FXML
    private Button b02;
    @FXML
    private Button b10;
    @FXML
    private Button b11;
    @FXML
    private Button b12;
    @FXML
    private Button b20;
    @FXML
    private Button b21;
    @FXML
    private Button b22;
    @FXML
    private ImageView brownPerson;
    @FXML
    private Text leftName;
    @FXML
    private Text leftNumber;
    @FXML
    private ImageView redPerson;
    @FXML
    private Text rightName;
    @FXML
    private Text rightNumber;
    
    final static int COMPUTER_MODE = 1;
    final static int MULTIPLAYER_MODE = 2;
    final static int ONLINE_MODE = 3;
    
    Image xImg = new Image("tictactoe/assets/xBoard.png");
    Image oImg = new Image("tictactoe/assets/oBoard.png");
    
    private static int playMode = 1;
    private int currentShapeToDraw = 1;
    
    private ArrayList<Button> listOfButtons ;
    Alert alert;

     @FXML
    private void onQuitOrWithdrawPressed(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
            Parent mainRoot = loader.load();
            Scene mainScene = new Scene(mainRoot);
            Stage primaryStage = (Stage) quitBtn.getScene().getWindow();
            primaryStage.setScene(mainScene);
        } catch (IOException ex) {
            Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void setMode(int gameMode){
        playMode = gameMode;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listOfButtons = new ArrayList<>();
        alert = new Alert(Alert.AlertType.INFORMATION, "winner");
        listOfButtons.add(b00);
        listOfButtons.add(b01);
        listOfButtons.add(b02);
        listOfButtons.add(b10);
        listOfButtons.add(b11);
        listOfButtons.add(b12);
        listOfButtons.add(b20);
        listOfButtons.add(b21);
        listOfButtons.add(b22);
        
        if(playMode == COMPUTER_MODE){
            multiplayerMode();
        }else if(playMode == MULTIPLAYER_MODE){
        
        }else if(playMode == ONLINE_MODE){
        
        }
    }    
   
    
    private void multiplayerMode(){
        leftName.setText("YOU");
        rightName.setText("Computer");
        Image redComputer = new Image("/tictactoe/assets/redComputer.png");
        redPerson.setImage(redComputer);
        
        b00.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b00);
            }
        });
        
        b01.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b01);
            }
        });
        
        b02.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b02);
            }
        });
        
        b10.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b10);
            }
        });
        
        b11.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b11);
            }
        });
        
        b12.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b12);
            }
        });
        
        b20.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b20);
            }
        });
        
        b21.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b21);
            }
        });
        
        b22.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawShapeForMultiPlayerMode(b22);
            }
        });
        
    }
    
    
    
    private void drawShapeForMultiPlayerMode(Button btn){
        if(btn.getText() == ""){
            if(currentShapeToDraw % 2 != 0 ){
                drawOorX(btn, "x");
            }else if(currentShapeToDraw %2 == 0){
                drawOorX(btn, "o");
            }
            currentShapeToDraw++;
            checkWin();
        }
    }
    
    
    
    
    
    
    
    private void drawOorX(Button btn,String shape ){
        ImageView oimageView = new ImageView(oImg);
        ImageView ximageView = new ImageView(xImg);
        ximageView.setFitHeight(60);
        ximageView.setFitWidth(60);
        oimageView.setFitHeight(60);
        oimageView.setFitWidth(60);
        
        btn.setGraphic(shape == "o" ? oimageView : ximageView);
        btn.setTextFill(Color.TRANSPARENT);
        btn.setText(shape);
    }
    
     private void checkWin(){
   
        for(int i=0;i<listOfButtons.size();i+=3){
            if(listOfButtons.get(i).getText() == listOfButtons.get(i+1).getText() && listOfButtons.get(i).getText() == listOfButtons.get(i+2).getText() && listOfButtons.get(i).getText() != ""){
                alert.setContentText("Winner is "+ listOfButtons.get(i).getText());
                alert.show();
                freezeButton();
            }
        }

        for(int i=0;i<3;i++){
            if(listOfButtons.get(i).getText() == listOfButtons.get(i+3).getText() && listOfButtons.get(i).getText() == listOfButtons.get(i+6).getText() && listOfButtons.get(i).getText() != ""){
                alert.setContentText("Winner is "+ listOfButtons.get(i).getText());
                alert.show();
                freezeButton();
            }
        }

        if(listOfButtons.get(0).getText() == listOfButtons.get(4).getText() && listOfButtons.get(0).getText() == listOfButtons.get(8).getText() && listOfButtons.get(0).getText() != ""){
            alert.setContentText("Winner is "+ listOfButtons.get(2).getText());
            alert.show();
            freezeButton();
        }

        if(listOfButtons.get(2).getText() == listOfButtons.get(4).getText() && listOfButtons.get(2).getText() == listOfButtons.get(6).getText() && listOfButtons.get(2).getText() != ""){
            alert.setContentText("Winner is "+ listOfButtons.get(2).getText());
            alert.show();
            freezeButton();
        }
    }
     
    private void freezeButton(){
        b22.setDisable(true);
        b20.setDisable(true);
        b12.setDisable(true);
        b11.setDisable(true);
        b10.setDisable(true);
        b02.setDisable(true);
        b01.setDisable(true);
        b21.setDisable(true);
        b00.setDisable(true);
    }
    
    
}
