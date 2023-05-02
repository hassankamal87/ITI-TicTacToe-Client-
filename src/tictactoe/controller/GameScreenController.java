
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
import tictactoe.utility.GameLevel;
import tictactoe.utility.GameMode;
import tictactoe.utility.PlayerSympol;

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
    private ImageView leftIcon;
    @FXML
    private Text leftName;
    @FXML
    private Text leftNumber;
    @FXML
    private ImageView rightIcon;
    @FXML
    private Text rightName;
    @FXML
    private Text rightNumber;
    
    private ArrayList<Button> listOfButtons;
    Alert alert;
    Image xImg;
    Image oImg;
    private int currentNumber;
    
    private Stage primaryStage;
    private GameMode gameMode;
    private PlayerSympol playerSympol;
    private GameLevel gameLevel;
    private boolean wonFlag = false;
    
    
    public GameScreenController(){
        gameMode = GameMode.multiply;
        playerSympol = PlayerSympol.X;
        currentNumber = 1;
        
        xImg = new Image("tictactoe/assets/xBoard.png");
        oImg = new Image("tictactoe/assets/oBoard.png");
        listOfButtons = new ArrayList<>();
        alert = new Alert(Alert.AlertType.INFORMATION, "winner");
    }

    public GameScreenController(GameMode gameMode){
        this.gameMode = gameMode;
        this.playerSympol = PlayerSympol.X;
        currentNumber = 1;
        
        xImg = new Image("tictactoe/assets/xBoard.png");
        oImg = new Image("tictactoe/assets/oBoard.png");
        listOfButtons = new ArrayList<>();
        alert = new Alert(Alert.AlertType.INFORMATION, "winner");
    }
    
    public GameScreenController(PlayerSympol playerSympol,GameLevel gameLevel){
        this.gameMode = GameMode.computer;
        this.playerSympol = playerSympol;
        this.gameLevel = gameLevel;
        currentNumber = 1;
        
        xImg = new Image("tictactoe/assets/xBoard.png");
        oImg = new Image("tictactoe/assets/oBoard.png");
        listOfButtons = new ArrayList<>();
        alert = new Alert(Alert.AlertType.INFORMATION, "winner");      
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listOfButtons.add(b00);
        listOfButtons.add(b01);
        listOfButtons.add(b02);
        listOfButtons.add(b10);
        listOfButtons.add(b11);
        listOfButtons.add(b12);
        listOfButtons.add(b20);
        listOfButtons.add(b21);
        listOfButtons.add(b22);
        
        if(gameMode == GameMode.computer){
            if(gameLevel == GameLevel.EASY){
            
            }else if(gameLevel == GameLevel.MEDIUM){
            
            }else if(gameLevel == GameLevel.HARD){
            
            }
        }else if(gameMode == GameMode.multiply){
            multiplayerMode();
            
        }else if(gameMode == GameMode.online){
            System.out.println("online");
        }
    }

    
    @FXML
    private void leaveMatch(MouseEvent event) throws Exception {
        primaryStage = (Stage) quitBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
        Parent mainRoot = loader.load();
        Scene mainScene = new Scene(mainRoot,610,410);
        primaryStage.setScene(mainScene);
        
       /* primaryStage = (Stage) quitBtn.getScene().getWindow();
        Parent mainRoot = FXMLLoader.load(getClass().getResource("XML/MainScreenUi.fxml"));
        Scene mainScene = new Scene(mainRoot, 600, 400);
        primaryStage.setScene(mainScene);*/
    }
    
    
    private void setIconAndNamePlaceInScreen(){
        if(playerSympol == PlayerSympol.O){
            leftName.setText("COMPUTER");
            leftIcon.setImage(new Image("/tictactoe/assets/brownComputer.png"));
            rightName.setText("YOU");
        }else if(playerSympol == PlayerSympol.X){
            rightName.setText("COMPUTER");
            rightIcon.setImage(new Image("/tictactoe/assets/redComputer.png"));
            leftName.setText("YOU");
        }
    }
   
    
    private void multiplayerMode(){
        
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
            if(currentNumber % 2 != 0 ){
                drawOorX(btn, PlayerSympol.X);
            }else if(currentNumber %2 == 0){
                drawOorX(btn, PlayerSympol.O);
            }
            currentNumber++;
            try {
                checkWin();
            } catch (IOException ex) {
                Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void drawOorX(Button btn, PlayerSympol sympol){
        ImageView oImageView = new ImageView(oImg);
        ImageView xImageView = new ImageView(xImg);
        
        xImageView.setFitHeight(60);
        xImageView.setFitWidth(60);
        oImageView.setFitHeight(60);
        oImageView.setFitWidth(60);
       
        btn.setGraphic(sympol == PlayerSympol.O ? oImageView : xImageView);
        btn.setTextFill(Color.TRANSPARENT);
        btn.setText(sympol == PlayerSympol.O ? "o" : "x");
    }
    
     private void checkWin() throws IOException{
   
        for(int i=0;i<listOfButtons.size();i+=3){
            if(listOfButtons.get(i).getText() == listOfButtons.get(i+1).getText() && listOfButtons.get(i).getText() == listOfButtons.get(i+2).getText() && listOfButtons.get(i).getText() != ""){
                /*alert.setContentText("Winner is "+ listOfButtons.get(i).getText());
                alert.show();
                freezeButton();*/
                wonFlag = true;
                resultScreen();
            }
        }

        for(int i=0;i<3;i++){
            if(listOfButtons.get(i).getText() == listOfButtons.get(i+3).getText() && listOfButtons.get(i).getText() == listOfButtons.get(i+6).getText() && listOfButtons.get(i).getText() != ""){
                /*alert.setContentText("Winner is "+ listOfButtons.get(i).getText());
                alert.show();
                freezeButton();*/
                wonFlag = true;
                resultScreen();
            }
        }

        if(listOfButtons.get(0).getText() == listOfButtons.get(4).getText() && listOfButtons.get(0).getText() == listOfButtons.get(8).getText() && listOfButtons.get(0).getText() != ""){
            /*alert.setContentText("Winner is "+ listOfButtons.get(2).getText());
            alert.show();
            freezeButton();*/
            wonFlag = true;
            resultScreen();
        }

        if(listOfButtons.get(2).getText() == listOfButtons.get(4).getText() && listOfButtons.get(2).getText() == listOfButtons.get(6).getText() && listOfButtons.get(2).getText() != ""){
            /*alert.setContentText("Winner is "+ listOfButtons.get(2).getText());
            alert.show();
            freezeButton();*/
            wonFlag = true;
            resultScreen();
        }
        if(currentNumber == 10 && !wonFlag){
            alert.setContentText("Draw");
            alert.show();
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
    
    private void resultScreen() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/ResultScreen.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,610,410);
            Stage stage = (Stage) quitBtn.getScene().getWindow();
            stage.setScene(scene);
    }
    
    
}



/*
    FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/GameScreen.fxml"));
    Parent gameRoot = loader.load();
    GameScreenController gameScreenController = new GameScreenController();
    loader.setController(gameScreenController);
    Scene gameScene = new Scene(gameRoot, 600, 400);
*/

/*
    FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/GameScreen.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> clazz) {
                if (clazz == GameScreenController.class) {
                    return new GameScreenController(GameMode.multiply );
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

        Scene gameScene = new Scene(gameRoot, 600, 400);
*/
