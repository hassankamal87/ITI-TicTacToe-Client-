package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tictactoe.Result;
import tictactoe.utility.GameLevel;
import tictactoe.utility.GameMode;
import tictactoe.utility.MatchStatus;
import static tictactoe.utility.MatchStatus.DRAW;
import static tictactoe.utility.MatchStatus.LOSE;
import static tictactoe.utility.MatchStatus.WIN;
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
    private ArrayList<Button> avaiableList;
    Image xImg;
    Image oImg;
    private int currentNumber;

    SequentialTransition sequentialTransition;
    private Stage primaryStage;
    private GameMode gameMode;
    private PlayerSympol playerSympol;
    private GameLevel gameLevel;
    private PlayerSympol winnerSympol;
    private MatchStatus matchStatus;
    private int leftSideScore = 0;
    private int rightSideScore = 0;
    private Line line;
    @FXML
    private AnchorPane containerPane;
    private Result result;

    public GameScreenController(GameMode gameMode) {
        this.gameMode = gameMode;
        this.playerSympol = PlayerSympol.X;

    }

    public GameScreenController(PlayerSympol playerSympol, GameLevel gameLevel) {
        this.gameMode = GameMode.computer;
        this.playerSympol = playerSympol;
        this.gameLevel = gameLevel;

    }

    //generated only in result screen controller
    public GameScreenController(Result result) {
        this.result = result;
        System.out.println(result.toString() + "From Game");
        this.gameMode = result.getMode();
        this.playerSympol = result.getSympol();
        this.gameLevel = result.getLevel();
        this.leftSideScore = result.getLeftSideScore();
        this.rightSideScore = result.getRightSideScore();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentNumber = 1;
        xImg = new Image("tictactoe/assets/xBoard.png");
        oImg = new Image("tictactoe/assets/oBoard.png");
        listOfButtons = new ArrayList<>();
        avaiableList = new ArrayList<>();
        listOfButtons.add(b00);
        listOfButtons.add(b01);
        listOfButtons.add(b02);
        listOfButtons.add(b10);
        listOfButtons.add(b11);
        listOfButtons.add(b12);
        listOfButtons.add(b20);
        listOfButtons.add(b21);
        listOfButtons.add(b22);
        line = new Line();
        line.setStrokeWidth(3);
        line.setStroke(Color.BLACK);
        line.setOpacity(1);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        avaiableList = (ArrayList<Button>) listOfButtons.clone();
        rightNumber.setText(rightSideScore + "");
        leftNumber.setText(leftSideScore + "");

        if (gameMode == GameMode.computer) {
            setIconAndNamePlaceInScreen();
            computerMode();
        } else if (gameMode == GameMode.multiplayer) {
            multiplayerMode();
        } else if (gameMode == GameMode.online) {
            System.out.println("online");
        }
    }

    private void computerMode() {
        setIconAndNamePlaceInScreen();
        gameKindTextView.setText(gameLevel.toString());

        if (playerSympol == PlayerSympol.O) {
            easyMove();
        }

        for (Button button : listOfButtons) {
            button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    drawShapeForComputer(button);
                }
            });
        }
    }

    @FXML
    private void leaveMatch(MouseEvent event) throws Exception {

        primaryStage = (Stage) quitBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
        Parent mainRoot = loader.load();
        Scene mainScene = new Scene(mainRoot, 610, 410);
        stopDrawLine();
        primaryStage.setScene(mainScene);
    }

    private void stopDrawLine() {
        if (sequentialTransition != null) {
            sequentialTransition.stop();
        }
    }

    private void setIconAndNamePlaceInScreen() {
        if (playerSympol == PlayerSympol.O) {
            leftName.setText("COMPUTER");
            leftIcon.setImage(new Image("/tictactoe/assets/brownComputer.png"));
            rightName.setText("YOU");
        } else if (playerSympol == PlayerSympol.X) {
            rightName.setText("COMPUTER");
            rightIcon.setImage(new Image("/tictactoe/assets/redComputer.png"));
            leftName.setText("YOU");
        }
    }

    private void multiplayerMode() {
        gameKindTextView.setText(gameMode.toString());

        for (Button button : listOfButtons) {
            button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    drawShapeForMultiPlayerMode(button);
                }
            });
        }
    }

    private void drawShapeForComputer(Button btn) {
        if (btn.getText() == "") {
            drawOorX(btn, playerSympol == PlayerSympol.X ? PlayerSympol.X : PlayerSympol.O);
            avaiableList.remove(btn);
            checkWin();
            if (matchStatus != WIN) {
                if (gameLevel == GameLevel.EASY) {
                    easyMove();
                }
            }
        }
    }

    private void easyMove() {
        if (avaiableList.size() > 0) {
            Random random = new Random();
            int indexToDraw = random.nextInt(avaiableList.size());
            drawOorX(avaiableList.get(indexToDraw), playerSympol == PlayerSympol.X ? PlayerSympol.O : PlayerSympol.X);
            avaiableList.remove(indexToDraw);
            checkWin();
        }
    }

    private void drawShapeForMultiPlayerMode(Button btn) {
        if (btn.getText() == "") {
            if (currentNumber % 2 != 0) {
                drawOorX(btn, PlayerSympol.X);
            } else if (currentNumber % 2 == 0) {
                drawOorX(btn, PlayerSympol.O);
            }
            avaiableList.remove(btn);
            currentNumber++;
            checkWin();
        }
    }

    private void drawOorX(Button btn, PlayerSympol sympol) {
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

    private void checkWin() {

        for (int i = 0; i < listOfButtons.size(); i += 3) {
            if (listOfButtons.get(i).getText() == listOfButtons.get(i + 1).getText() && listOfButtons.get(i).getText() == listOfButtons.get(i + 2).getText() && listOfButtons.get(i).getText() != "") {

                winnerSympol = listOfButtons.get(i).getText() == "x" ? playerSympol.X : PlayerSympol.O;
                line.setStartX(listOfButtons.get(i).getLayoutX() + (listOfButtons.get(i).getWidth() / 2) - 20);
                line.setStartY(listOfButtons.get(i).getLayoutY() + (listOfButtons.get(i).getHeight() / 2));
                line.setEndX(listOfButtons.get(i + 2).getLayoutX() + (listOfButtons.get(i).getWidth() / 2) + 15);
                line.setEndY(listOfButtons.get(i + 2).getLayoutY() + (listOfButtons.get(i).getHeight() / 2));
                matchStatus = WIN;
                animateResultAndGoToResult();
                return;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (listOfButtons.get(i).getText() == listOfButtons.get(i + 3).getText() && listOfButtons.get(i).getText() == listOfButtons.get(i + 6).getText() && listOfButtons.get(i).getText() != "") {
                winnerSympol = listOfButtons.get(i).getText() == "x" ? playerSympol.X : PlayerSympol.O;
                line.setStartX(listOfButtons.get(i).getLayoutX() + (listOfButtons.get(i).getWidth() / 2) - 5);
                line.setStartY(listOfButtons.get(i).getLayoutY());
                line.setEndX(listOfButtons.get(i + 6).getLayoutX() + (listOfButtons.get(i).getWidth() / 2) - 5);
                line.setEndY(listOfButtons.get(i + 6).getLayoutY() + (listOfButtons.get(i).getHeight()));
                matchStatus = WIN;
                animateResultAndGoToResult();
                return;
            }
        }

        if (listOfButtons.get(0).getText() == listOfButtons.get(4).getText() && listOfButtons.get(0).getText() == listOfButtons.get(8).getText() && listOfButtons.get(0).getText() != "") {
            winnerSympol = listOfButtons.get(0).getText() == "x" ? playerSympol.X : PlayerSympol.O;
            line.setStartX(listOfButtons.get(0).getLayoutX());
            line.setStartY(listOfButtons.get(0).getLayoutY());
            line.setEndX(listOfButtons.get(8).getLayoutX() + (listOfButtons.get(8).getWidth()));
            line.setEndY(listOfButtons.get(8).getLayoutY() + (listOfButtons.get(8).getHeight()));
            matchStatus = WIN;
            animateResultAndGoToResult();
            return;
        }

        if (listOfButtons.get(2).getText() == listOfButtons.get(4).getText() && listOfButtons.get(2).getText() == listOfButtons.get(6).getText() && listOfButtons.get(2).getText() != "") {
            winnerSympol = listOfButtons.get(2).getText() == "x" ? playerSympol.X : PlayerSympol.O;
            line.setStartX(listOfButtons.get(2).getLayoutX() + (b00.getWidth()));
            line.setStartY(listOfButtons.get(2).getLayoutY());
            line.setEndX(listOfButtons.get(6).getLayoutX());
            line.setEndY(listOfButtons.get(6).getLayoutY() + (b00.getHeight()));
            matchStatus = WIN;
            animateResultAndGoToResult();
            return;
        }
        if (avaiableList.size() == 0 && matchStatus != WIN) {
            System.out.println("draw");
            matchStatus = DRAW;
            resultScreen();
        }

    }

    private void freezeButton() {
        for (Button button : listOfButtons) {
            button.setDisable(true);
            button.setStyle("-fx-opacity: 1.0;-fx-background-color:  transparent");
        }
    }

    private void animateResultAndGoToResult() {
        containerPane.getChildren().add(line);
        freezeButton();
        ScaleTransition scale;
        scale = new ScaleTransition();
        scale.setNode(line);
        scale.setDuration(Duration.millis(2000));
        scale.setCycleCount(1);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(1);
        scale.setToY(1);
        sequentialTransition = new SequentialTransition(scale);
        sequentialTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultScreen();
            }
        });
        sequentialTransition.play();
    }

    private void resultScreen() {

        try {
            getMatchStatus();
            result = new Result(gameMode, gameLevel, playerSympol, matchStatus, leftSideScore, rightSideScore);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/ResultScreen.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> clazz) {
                    if (clazz == ResultScreenController.class) {
                        return new ResultScreenController(result);
                    } else {
                        try {
                            return clazz.newInstance();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
            Parent resultRoot = loader.load();

            Scene resultScene = new Scene(resultRoot, 610, 410);
            primaryStage = (Stage) quitBtn.getScene().getWindow();
            if (primaryStage != null) {
                primaryStage.setScene(resultScene);
            }

        } catch (IOException ex) {
            Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getMatchStatus() {
        if (winnerSympol != null) {
            if (winnerSympol == playerSympol) {
                if (playerSympol == playerSympol.O) {
                    rightSideScore++;
                } else {
                    leftSideScore++;
                }
            } else if (winnerSympol != playerSympol) {
                matchStatus = LOSE;
                if (playerSympol == playerSympol.O) {
                    leftSideScore++;
                } else {
                    rightSideScore++;
                }
            }
        } else {
            matchStatus = DRAW;
        }
    }
}
