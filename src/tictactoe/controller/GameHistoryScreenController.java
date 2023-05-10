/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.utility.GameMode;

/**
 * FXML Controller class
 *
 * @author Mohamed Adel
 */
public class GameHistoryScreenController implements Initializable {

    @FXML
    private ListView<?> listItemHolder;

    @FXML
    private ImageView backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File recordPackage = new File(".\\src\\tictactoe\\recordingFile");
        String[] listOfFileNames = recordPackage.list();
        ArrayList<Node> list = new ArrayList();

        for(String fileName : listOfFileNames){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/HistoryItemHolder.fxml"));
                loader.setControllerFactory(new Callback<Class<?>, Object>() {
                    @Override
                    public Object call(Class<?> clazz) {
                        if (clazz == HistoryItemHolderController.class) {
                            return new HistoryItemHolderController(fileName);
                        } else {
                            try {
                                return clazz.newInstance();
                            } catch (Exception ex) {
                                System.out.println("here");
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
        
                
                Node node = loader.load();
                list.add(node);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        if(listOfFileNames.length!=0){
        ObservableList items = FXCollections.observableArrayList(list);
        listItemHolder.setItems(items);}

        
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tictactoe/XML/MainScreenUi.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) listItemHolder.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(OnlineFriendListScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
