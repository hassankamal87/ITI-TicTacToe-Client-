package tictactoe;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
        Parent mainRoot = FXMLLoader.load(getClass().getResource("XML/MainScreenUi.fxml"));

        Scene splashScene = new Scene(splashRoot, 600, 400);
        Scene mainScene = new Scene(mainRoot, 610, 410);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(splashScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        endSplashScreen(primaryStage, splashRoot, mainScene);

    }

    @Override
    public void stop() throws Exception {
        Connection.getInstance().closeConnection();
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
