package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
    //This is just an example the board will be provided by the C++ libraries where the logic is defined.
    private int[][] gameBoard = {
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
        Board reversiBoard = new Board(gameBoard);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        reversiBoard.setTranslateX(10);
        reversiBoard.setTranslateY(10);
        reversiBoard.setOnMouseClicked(
                e-> {
                    if (currentPlayer.getText().equals("Black"))
                        gameBoard[reversiBoard.getClickedChildNode().GetY()][reversiBoard.getClickedChildNode().GetX()] = 1;
                    else
                        gameBoard[reversiBoard.getClickedChildNode().GetY()][reversiBoard.getClickedChildNode().GetX()] = 2;
                    reversiBoard.draw();
                    //We will run this method to handle the actual game process.
                    //runGameFlow();
                }
        );
        root.getChildren().add(reversiBoard);
        reversiBoard.draw();
    }

    public void runGameFlow () {

    }
}
