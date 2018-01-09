package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kostarubtsov1990 on 07/01/18.
 */
public class ReversiGameController implements Initializable {
    @FXML
    private Pane root;
    @FXML
    private Text currentPlayer;
    ReversiGame game;

    int reversiBoardSize;
    String startingPlayer;
    Color firstPlayer, secondPlayer;


    //This is just an example the board will be provided by the C++ libraries where the logic is defined.
    private int[][] reversiGameBoard = {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,2,0,0,0},
            {0,0,0,2,1,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
    };
    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        LoadSettingsInfo ();
        Board reversiBoard = new Board(reversiBoardSize, firstPlayer, secondPlayer);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        reversiBoard.setTranslateX(10);
        reversiBoard.setTranslateY(10);
        reversiBoard.setOnMouseClicked(
                e-> {
                    runGameFlow(currentPlayer.getText());
                    reversiBoard.draw();

                }
        );
        root.getChildren().add(reversiBoard);
        reversiBoard.draw();
        game = new ReversiGame(reversiBoard);
    }

    public void runGameFlow (String currentPlayer) {
        //Here we will implement the player turn handling

    }

    private void LoadSettingsInfo () {
        try(BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {
            startingPlayer = br.readLine();

            String playersColors = br.readLine();
            String [] parts = playersColors.split(":");
            String [] partsByComma = parts[1].split(",");
            String firstPlayerColor = partsByComma[1];
            String secondPlayerColor = parts[2];

            SetColor(firstPlayerColor, secondPlayerColor);

            reversiBoardSize = Integer.parseInt(br.readLine());

        } catch (Exception exception) {}
    }

    void SetColor (String firstPlayer, String secondPlayer) {
        //temporary set these colors
        this.firstPlayer = Color.BLACK;
        this.secondPlayer = Color.WHITE;
    }


}
