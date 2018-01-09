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



    private String startPlayer;
    private String symField;
    private String sizeField;

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
    protected void gameHandler () throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GameStylePattern.fxml"));
        primaryStage.setTitle("Reversi Game");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
}
