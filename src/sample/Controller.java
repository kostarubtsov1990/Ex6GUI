package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller{
    @FXML
    private Button button;
    @FXML
    private TextField startingPlayer;
    @FXML
    private TextField symbolField;
    @FXML
    private TextField boardSizeField;
    @FXML
    private Text messageText;


    private String startPlayer;
    private String symField;
    private String sizeField;

    public Controller() {
        System.out.println("bla");
    }

    @FXML
    protected void settingsHandler() throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
        //button.setEffect(new DropShadow());
    }
    @FXML
    protected void AcceptSetttings () {
        startPlayer = startingPlayer.getText();
        Context.getInstance().SetPlayer(startPlayer);
        symField = symbolField.getText();
        sizeField = boardSizeField.getText();
        messageText.setText("Settings accepted!");
        messageText.setFill(Color.BLUE);
    }
    @FXML
    protected void gameHandler () throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GameStylePattern.fxml"));
        primaryStage.setTitle("Reversi Game");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
}
