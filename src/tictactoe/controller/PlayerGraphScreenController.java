/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author WIN 10
 */
public class PlayerGraphScreenController implements Initializable {

    @FXML
    private Text onlineNum;
    @FXML
    private Text onlinePlayersText;
    @FXML
    private Text offlineNum;
    @FXML
    private Text offlinePlayersText;
    @FXML
    PieChart pieChart;

     
             
    public void initialize(URL url, ResourceBundle rb) {
       
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( 
            new PieChart.Data(onlinePlayersText.getText(), Integer.parseInt(onlineNum.getText())), 
            new PieChart.Data(offlinePlayersText.getText(), Integer.parseInt(offlineNum.getText())));
        
        pieChart.setData(pieChartData);   
        
    } 

    public PlayerGraphScreenController() {
    }
    
    
}
