package tictactoe.XML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class AcceptInvitationScreenBase extends Pane {

    protected final ImageView imageView;
    protected final Button StartButton;
    protected final CheckBox CheckBox;
    protected final Text WaitingText;

    public AcceptInvitationScreenBase() {

        imageView = new ImageView();
        StartButton = new Button();
        CheckBox = new CheckBox();
        WaitingText = new Text();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(400.0);
        imageView.setFitWidth(600.0);
        imageView.setImage(new Image(getClass().getResource("../assets/Background.jpg").toExternalForm()));

        StartButton.setLayoutX(329.0);
        StartButton.setLayoutY(287.0);
        StartButton.setMnemonicParsing(false);
        StartButton.setPrefHeight(60.0);
        StartButton.setPrefWidth(147.0);
        StartButton.setStyle("-fx-background-color: #B45f06; -fx-background-radius: 10px;");
        StartButton.setText("Start");
        StartButton.setFont(new Font(28.0));

        CheckBox.setLayoutX(341.0);
        CheckBox.setLayoutY(38.0);
        CheckBox.setMnemonicParsing(false);
        CheckBox.setPrefHeight(32.0);
        CheckBox.setPrefWidth(233.0);
        CheckBox.setStyle("-fx-background-color: #B45f06;");
        CheckBox.setText("Record Game");
        CheckBox.setFont(new Font(18.0));

        WaitingText.setLayoutX(265.0);
        WaitingText.setLayoutY(170.0);
        WaitingText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        WaitingText.setStrokeWidth(0.0);
        WaitingText.setText("+Player now is waiting for you");
        WaitingText.setFont(new Font(24.0));

        getChildren().add(imageView);
        getChildren().add(StartButton);
        getChildren().add(CheckBox);
        getChildren().add(WaitingText);

    }
}
