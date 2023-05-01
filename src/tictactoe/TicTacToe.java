package tictactoe;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tictactoe.controller.GameScreenController;
import tictactoe.utility.GameMode;
import tictactoe.utility.PlayerSympol;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent splashRoot = FXMLLoader.load(getClass().getResource("XML/SplashScreen.fxml"));
        //Parent mainRoot = FXMLLoader.load(getClass().getResource("XML/MainScreenUi.fxml"));

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

        Scene splashScene = new Scene(splashRoot, 600, 400);
        //Scene mainScene = new Scene(mainRoot, 600, 400);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(splashScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        endSplashScreen(primaryStage, splashRoot, gameScene);

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void endSplashScreen(Stage primaryStage, Node splash, Scene mainScene) {
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(4), splash);
        fadeSplash.setFromValue(1);
        fadeSplash.setToValue(1);

        SequentialTransition sequentialTransition = new SequentialTransition(fadeSplash);
        sequentialTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(mainScene);
                primaryStage.show();
            }
        });

        sequentialTransition.play();
    }

}
