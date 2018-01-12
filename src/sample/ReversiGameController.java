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
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by kostarubtsov1990 on 07/01/18.
 */
public class ReversiGameController implements Initializable {
    @FXML
    private Pane root;
    @FXML
    private Text currentPlayer;
    private ReversiGame game;

    private int reversiBoardSize;
    private String startingPlayer;
    private Color firstPlayer, secondPlayer;
    private Map<String, Integer> playerNameToSymbolValue;

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
        game = new ReversiGame(reversiBoard, new DefaultReversiGameLogic());
    }

    public void runGameFlow (String currentPlayer) {
        Board board = game.GetBoard();
        GameLogic logic = game.GetGameLogic();

        if (!logic.IsGameOver(ConvertSymbolArrayToIntArray (board.GetBoard()))) {

            logic.CheckPossibleMoves(ConvertSymbolArrayToIntArray (board.GetBoard()),
                    playerNameToSymbolValue.get(currentPlayer));

            //need to check if the vector of c++ side pointer to dynamic allocation of GameLogic is empty or not
            //to add appropriate method

            if (!logic.IsPossibleMoveExist()) {
                //to do the appropriate steps to show the user that there no turn for him
                return;
            }

            if (!logic.CheckLocation(board.getClickedChildNode().GetY(), board.getClickedChildNode().GetX())) {
                //to ask the user (show him on the GUI) to try again
                return;
            }

            int [][] updatedBoardContent = logic.UpdateBoard(ConvertSymbolArrayToIntArray (board.GetBoard()),
                    board.getClickedChildNode().GetY(), board.getClickedChildNode().GetX(),
                    playerNameToSymbolValue.get(currentPlayer));

            //TO DO: add a function or maybe some generic method to existing function of conversion below,
            //to convert the primitive int 2d array board content to enum symbol type

        }

        else {
            String winner = "";
            Integer winnerValue = logic.DeclareWinner(ConvertSymbolArrayToIntArray (board.GetBoard()));
            for (Map.Entry <String, Integer> entry: playerNameToSymbolValue.entrySet()) {
                if(winnerValue.equals(entry.getValue())){
                    winner = entry.getKey();
                    break; //breaking because its one to one map
                }
            }

            //TO DO: show the winner on the GUI and appropriate messege that the game is over

        }

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

    private int [][] ConvertSymbolArrayToIntArray (Board.symbol [][] symbolBoard) {
        int row = symbolBoard.length;
        int col = symbolBoard[0].length;

        int [][] board = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = symbolBoard[i][j].ordinal();
            }
        }
        return board;
    }
}
