package tictactoe;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreenUi extends AnchorPane {

    protected final ImageView imageView;
    protected final Button computerBtn;
    protected final HBox hBox;
    protected final ImageView imageView0;
    protected final Text text;
    protected final ImageView imageView1;
    protected final Button localBtn;
    protected final HBox hBox0;
    protected final ImageView imageView2;
    protected final Text text0;
    protected final ImageView imageView3;
    protected final Button onlineBtn;
    protected final HBox hBox1;
    protected final ImageView imageView4;
    protected final Text text1;
    protected final Button signInBtn;
    protected final HBox hBox2;
    protected final ImageView imageView5;
    protected final Text text2;
    protected final Button signUpBtn;
    protected final Button historyBtn;
    protected final Button logoutBtn;
    protected final HBox hBox3;
    protected final ImageView imageView6;
    protected final Text text3;
    protected final Text UsernameTxt;
    protected final Text text4;
    protected final Text text5;
    private Stage primaryStage;

    public MainScreenUi(Stage primaryStage) {
        this.primaryStage = primaryStage;
        imageView = new ImageView();
        computerBtn = new Button();
        hBox = new HBox();
        imageView0 = new ImageView();
        text = new Text();
        imageView1 = new ImageView();
        localBtn = new Button();
        hBox0 = new HBox();
        imageView2 = new ImageView();
        text0 = new Text();
        imageView3 = new ImageView();
        onlineBtn = new Button();
        hBox1 = new HBox();
        imageView4 = new ImageView();
        text1 = new Text();
        signInBtn = new Button();
        hBox2 = new HBox();
        imageView5 = new ImageView();
        text2 = new Text();
        signUpBtn = new Button();
        historyBtn = new Button();
        logoutBtn = new Button();
        hBox3 = new HBox();
        imageView6 = new ImageView();
        text3 = new Text();
        UsernameTxt = new Text();
        text4 = new Text();
        text5 = new Text();

        setId("AnchorPane");
        setPrefHeight(412.0);
        setPrefWidth(613.0);

        imageView.setFitHeight(412.0);
        imageView.setFitWidth(637.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("assets/Background.jpg").toExternalForm()));

        computerBtn.setAlignment(javafx.geometry.Pos.CENTER);
        computerBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        computerBtn.setLayoutX(336.0);
        computerBtn.setLayoutY(119.0);
        computerBtn.setMnemonicParsing(false);
        computerBtn.setPrefHeight(40.0);
        computerBtn.setPrefWidth(240.0);
        computerBtn.setStyle("-fx-background-color: #B45F06; -fx-background-radius: 10px;");
        computerBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setPrefHeight(31.0);
        hBox.setPrefWidth(5.0);

        imageView0.setFitHeight(31.0);
        imageView0.setFitWidth(60.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("assets/user_icon.png").toExternalForm()));

        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("VS ");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setWrappingWidth(78.13671875);
        text.setFont(new Font(28.0));

        imageView1.setFitHeight(31.0);
        imageView1.setFitWidth(39.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("assets/computer_icon.png").toExternalForm()));
        computerBtn.setGraphic(hBox);

        localBtn.setAlignment(javafx.geometry.Pos.CENTER);
        localBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        localBtn.setLayoutX(336.0);
        localBtn.setLayoutY(180.0);
        localBtn.setMnemonicParsing(false);
        localBtn.setPrefHeight(40.0);
        localBtn.setPrefWidth(240.0);
        localBtn.setStyle("-fx-background-color: #B45F06; -fx-background-radius: 10px;");
        localBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        hBox0.setAlignment(javafx.geometry.Pos.CENTER);
        hBox0.setPrefHeight(31.0);
        hBox0.setPrefWidth(78.0);

        imageView2.setFitHeight(31.0);
        imageView2.setFitWidth(60.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("assets/user_icon.png").toExternalForm()));

        text0.setFill(javafx.scene.paint.Color.WHITE);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("VS");
        text0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text0.setWrappingWidth(78.13671875);
        text0.setFont(new Font(28.0));

        imageView3.setFitHeight(31.0);
        imageView3.setFitWidth(39.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("assets/user_icon.png").toExternalForm()));
        localBtn.setGraphic(hBox0);

        onlineBtn.setAlignment(javafx.geometry.Pos.CENTER);
        onlineBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        onlineBtn.setDisable(true);
        onlineBtn.setLayoutX(336.0);
        onlineBtn.setLayoutY(240.0);
        onlineBtn.setMnemonicParsing(false);
        onlineBtn.setPrefHeight(40.0);
        onlineBtn.setPrefWidth(240.0);
        onlineBtn.setStyle("-fx-background-color: #B45F06; -fx-background-radius: 10px;");
        onlineBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        hBox1.setAlignment(javafx.geometry.Pos.CENTER);
        hBox1.setPrefHeight(32.0);
        hBox1.setPrefWidth(241.0);

        imageView4.setFitHeight(31.0);
        imageView4.setFitWidth(60.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("assets/online_network_icon.png").toExternalForm()));

        text1.setFill(javafx.scene.paint.Color.WHITE);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("Online Game");
        text1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text1.setWrappingWidth(165.13671875);
        text1.setFont(new Font(28.0));
        onlineBtn.setGraphic(hBox1);

        signInBtn.setAlignment(javafx.geometry.Pos.CENTER);
        signInBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        signInBtn.setLayoutX(497.0);
        signInBtn.setLayoutY(14.0);
        signInBtn.setMnemonicParsing(false);
        signInBtn.setPrefHeight(26.0);
        signInBtn.setPrefWidth(101.0);
        signInBtn.setStyle("-fx-background-color: #666666;");
        signInBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        signInBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        signInBtn.setFont(new Font("System Bold", 16.0));

        hBox2.setAlignment(javafx.geometry.Pos.CENTER);
        hBox2.setPrefHeight(32.0);
        hBox2.setPrefWidth(241.0);
        hBox2.setSpacing(3.0);

        imageView5.setFitHeight(27.0);
        imageView5.setFitWidth(19.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        imageView5.setImage(new Image(getClass().getResource("assets/sign_in_icon.png").toExternalForm()));

        text2.setFill(javafx.scene.paint.Color.WHITE);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("Sign In");
        text2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text2.setWrappingWidth(55.13671875);
        text2.setFont(new Font("System Bold", 16.0));
        signInBtn.setGraphic(hBox2);

        signUpBtn.setAlignment(javafx.geometry.Pos.CENTER);
        signUpBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        signUpBtn.setLayoutX(497.0);
        signUpBtn.setLayoutY(56.0);
        signUpBtn.setMnemonicParsing(false);
        signUpBtn.setPrefHeight(26.0);
        signUpBtn.setPrefWidth(101.0);
        signUpBtn.setStyle("-fx-background-color: #666666;");
        signUpBtn.setText("Sign Up");
        signUpBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        signUpBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        signUpBtn.setFont(new Font("System Bold", 16.0));

        historyBtn.setAlignment(javafx.geometry.Pos.CENTER);
        historyBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        historyBtn.setDisable(true);
        historyBtn.setLayoutX(14.0);
        historyBtn.setLayoutY(353.0);
        historyBtn.setMnemonicParsing(false);
        historyBtn.setPrefHeight(26.0);
        historyBtn.setPrefWidth(101.0);
        historyBtn.setStyle("-fx-background-color: #666666;");
        historyBtn.setText("History");
        historyBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        historyBtn.setFont(new Font("System Bold", 16.0));

        logoutBtn.setAlignment(javafx.geometry.Pos.CENTER);
        logoutBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        logoutBtn.setDisable(true);
        logoutBtn.setLayoutX(497.0);
        logoutBtn.setLayoutY(14.0);
        logoutBtn.setMnemonicParsing(false);
        logoutBtn.setPrefHeight(26.0);
        logoutBtn.setPrefWidth(101.0);
        logoutBtn.setStyle("-fx-background-color: #BF0404;");
        logoutBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        logoutBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        logoutBtn.setVisible(false);
        logoutBtn.setFont(new Font("System Bold", 16.0));

        hBox3.setAlignment(javafx.geometry.Pos.CENTER);
        hBox3.setPrefHeight(32.0);
        hBox3.setPrefWidth(241.0);
        hBox3.setSpacing(3.0);

        imageView6.setFitHeight(27.0);
        imageView6.setFitWidth(22.0);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);
        imageView6.setImage(new Image(getClass().getResource("assets/log_out_icon.png").toExternalForm()));

        text3.setDisable(true);
        text3.setFill(javafx.scene.paint.Color.WHITE);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("Log out");
        text3.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text3.setWrappingWidth(58.13671875);
        text3.setFont(new Font("System Bold", 16.0));
        logoutBtn.setGraphic(hBox3);

        UsernameTxt.setLayoutX(14.0);
        UsernameTxt.setLayoutY(41.0);
        UsernameTxt.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        UsernameTxt.setStrokeWidth(0.0);
        UsernameTxt.setText("Welcome Player");
        UsernameTxt.setVisible(false);
        UsernameTxt.setWrappingWidth(194.62890625);
        UsernameTxt.setFont(new Font("System Bold", 24.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#b45f06"));
        text4.setLayoutX(479.0);
        text4.setLayoutY(356.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("Games: ");
        text4.setVisible(false);
        text4.setFont(new Font("System Bold", 20.0));

        text5.setFill(javafx.scene.paint.Color.valueOf("#b45f06"));
        text5.setLayoutX(479.0);
        text5.setLayoutY(383.0);
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("Score: ");
        text5.setVisible(false);
        text5.setFont(new Font("System Bold", 20.0));

        getChildren().add(imageView);
        hBox.getChildren().add(imageView0);
        hBox.getChildren().add(text);
        hBox.getChildren().add(imageView1);
        getChildren().add(computerBtn);
        hBox0.getChildren().add(imageView2);
        hBox0.getChildren().add(text0);
        hBox0.getChildren().add(imageView3);
        getChildren().add(localBtn);
        hBox1.getChildren().add(imageView4);
        hBox1.getChildren().add(text1);
        getChildren().add(onlineBtn);
        hBox2.getChildren().add(imageView5);
        hBox2.getChildren().add(text2);
        getChildren().add(signInBtn);
        getChildren().add(signUpBtn);
        getChildren().add(historyBtn);
        hBox3.getChildren().add(imageView6);
        hBox3.getChildren().add(text3);
        getChildren().add(logoutBtn);
        getChildren().add(UsernameTxt);
        getChildren().add(text4);
        getChildren().add(text5);
        
        
        
        //changing scene
//        computerBtn.addEventHandler(ActionEvent.ACTION,
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event
//            ) {
//                ChooseSideScreen root = new ChooseSideScreen(primaryStage); // destenation root
//                Scene scene = new Scene(root, 600, 500);
//                primaryStage.setScene(scene);
//            }
//        }
//        );


        //changing scene
//        localBtn.addEventHandler(ActionEvent.ACTION,
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event
//            ) {
//                Destenation root = new Destenation(primaryStage); // destenation root
//                Scene scene = new Scene(root, 600, 500);
//                primaryStage.setScene(scene);
//            }
//        }
//        );



//        onlineBtn.addEventHandler(ActionEvent.ACTION,
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event
//            ) {
//                Destenation root = new Destenation(primaryStage); // destenation root
//                Scene scene = new Scene(root, 600, 500);
//                primaryStage.setScene(scene);
//            }
//        }
//        );


        //changing scene
//        signInBtn.addEventHandler(ActionEvent.ACTION,
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event
//            ) {
//                Destenation root = new Destenation(primaryStage); // destenation root
//                Scene scene = new Scene(root, 600, 500);
//                primaryStage.setScene(scene);
//            }
//        }
//        );


        //changing scene
//        signUpBtn.addEventHandler(ActionEvent.ACTION,
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event
//            ) {
//                Destenation root = new Destenation(primaryStage); // destenation root
//                Scene scene = new Scene(root, 600, 500);
//                primaryStage.setScene(scene);
//            }
//        }
//        );


//        logOutBtn.addEventHandler(ActionEvent.ACTION,
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event
//            ) {
//                Destenation root = new Destenation(primaryStage); // destenation root
//                Scene scene = new Scene(root, 600, 500);
//                primaryStage.setScene(scene);
//            }
//        }
//        );

    }
}
