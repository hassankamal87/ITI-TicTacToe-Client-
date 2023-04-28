
package tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class TicTacToe extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        System.out.println("After Modification");
        System.out.println("Mohamed Adel");
        System.out.println("Danash");
         System.out.println("Shimaa");
        StackPane root = new StackPane();
        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
