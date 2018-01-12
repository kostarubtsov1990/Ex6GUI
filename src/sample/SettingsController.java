package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.imageio.IIOException;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kostarubtsov1990 on 09/01/18.
 */
public class SettingsController implements Initializable {

    @FXML
    private TextField startingPlayer;
    @FXML
    private TextField symbolColorField;
    @FXML
    private TextField boardSizeField;
    @FXML
    private Text messageText;
    @FXML


    @Override
    public void initialize(URL location, ResourceBundle
            resources) {

        File f = new File("settings.txt");
        if(!f.exists()) {
            try {
                PrintWriter writer = new PrintWriter("settings.txt", "UTF-8");
                writer.println("Black");
                writer.println("Black:Black, White:White");
                writer.println("8");
                writer.close();
            }catch (Exception excepion) {}
        }
        SetPromptText();
    }

    @FXML
    protected void AcceptSettings () {

        OverwriteCurrentSettings ();

        messageText.setText("Settings accepted!");
        messageText.setFill(Color.BLUE);
    }


    private void OverwriteCurrentSettings (){
        String inputText = "";
        try(BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {

            if (startingPlayer.getText().equals("") && symbolColorField.getText().equals("")
                    && boardSizeField.getText().equals("")) {
                return;

            } else {

                if (!startingPlayer.getText().equals("")) {
                    inputText += startingPlayer.getText() + "\n";
                    br.readLine();
                } else {
                    inputText += br.readLine() + "\n";;
                }

                if (!symbolColorField.getText().equals("")) {
                    inputText += symbolColorField.getText() + "\n";
                    br.readLine();
                } else {
                    inputText += br.readLine() + "\n";;
                }

                if (!boardSizeField.getText().equals("")) {
                    inputText += boardSizeField.getText() + "\n";
                } else {
                    inputText += br.readLine() + "\n";;
                }
            }

            PrintWriter writer = new PrintWriter("settings.txt");
            writer.println(inputText);
            writer.close();

        } catch (Exception exception) {}
    }

    private void SetPromptText () {

        try(BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {
            startingPlayer.setPromptText(br.readLine());
            symbolColorField.setPromptText(br.readLine());
            boardSizeField.setPromptText(br.readLine());

        } catch (Exception exception) {}
    }
}
