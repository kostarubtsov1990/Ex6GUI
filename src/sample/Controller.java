package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.awt.*;


public class Controller {
    @FXML
    private Button button;
@FXML
    protected void signIn() throws Exception{
    Stage primaryStage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
    primaryStage.setTitle("Settings");
    primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();
        //button.setEffect(new DropShadow());
    }
}
