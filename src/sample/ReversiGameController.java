package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
    @FXML
    private Text xPlayerScore;
    @FXML
    private Text oPlayerScore;
    private ReversiGame game;

    private int reversiBoardSize;
    private String startingPlayer;
    private Color firstPlayer, secondPlayer;
    private Map<String, Integer> playerNameToSymbolValue;

    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        playerNameToSymbolValue = new HashMap<>();
        playerNameToSymbolValue.put("Black", 0);
        playerNameToSymbolValue.put("White", 1);
        LoadSettingsInfo();
        currentPlayer.setText(startingPlayer);
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
        int [][] integerBoardContent = ConvertSymbolArrayToIntegerArray (board.GetBoard());
        if (!logic.IsGameOver(ConvertSymbolArrayToIntegerArray (board.GetBoard()))) {

            logic.CheckPossibleMoves(ConvertSymbolArrayToIntegerArray (board.GetBoard()),
                    playerNameToSymbolValue.get(currentPlayer));

            //need to check if the vector of c++ side pointer to dynamic allocation of GameLogic is empty or not
            //to add appropriate method

            if (!logic.IsPossibleMoveExist()) {
                SwitchSides(currentPlayer);
                //to do the appropriate steps to show the user that there no turn for him
                return;
            }

            if (!logic.CheckLocation(board.getClickedChildNode().GetY(), board.getClickedChildNode().GetX())) {
                //to ask the user (show him on the GUI) to try again
                return;
            }

            int [][] updatedBoardContent = logic.UpdateBoard(ConvertSymbolArrayToIntegerArray (board.GetBoard()),
                    board.getClickedChildNode().GetY(), board.getClickedChildNode().GetX(),
                    playerNameToSymbolValue.get(currentPlayer));

            SwitchSides(currentPlayer);

            board.SetBoard(ConvertIntegerArrayToSymbolArray(updatedBoardContent));

            board.draw();
            logic.UpdateScore(board.GetBoard());
            Score score = logic.GetScore();

            xPlayerScore.setText(Integer.toString(score.GetXScore()));
            oPlayerScore.setText(Integer.toString(score.GetOScore()));

            //TO DO: add a function or maybe some generic method to existing function of conversion below,
            //to convert the primitive int 2d array board content to enum symbol type

        }

        else {
            String winnerPlayer = "";
            Integer winnerValue = logic.DeclareWinner(ConvertSymbolArrayToIntegerArray (board.GetBoard()));
            for (Map.Entry <String, Integer> entry: playerNameToSymbolValue.entrySet()) {
                if(winnerValue.equals(entry.getValue())){
                    winnerPlayer = entry.getKey();
                    break; //breaking because its one to one map
                }
            }
            try {
                Stage primaryStage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("WinnerStage.fxml"));
                Parent root = loader.load();

                WinnerStageController winnerStageController = loader.<WinnerStageController>getController();
                if (!winnerPlayer.equals("")) {
                    winnerStageController.SetWinnerMessegeText(winnerPlayer + " is the winner!");
                }
                else {
                    winnerStageController.SetWinnerMessegeText("its a draw!");
                }
                primaryStage.setTitle("Winner");
                primaryStage.setScene(new Scene(root, 167, 68));
                primaryStage.showAndWait();

                // get a handle to the stage
                Stage stage = (Stage) root.getScene().getWindow();
                // do what you have to do
                stage.close();

            } catch (IOException exeption) {}

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


    private int [][] ConvertSymbolArrayToIntegerArray(Board.symbol [][] symbolBoard) {
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

    private Board.symbol[][] ConvertIntegerArrayToSymbolArray (int [][] integerBoard) {
        int row = integerBoard.length;
        int col = integerBoard[0].length;

        Board.symbol [][] board = new Board.symbol[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Board.symbol.values()[integerBoard[i][j]] ;
            }
        }
        return board;
    }

    private void SwitchSides(String currentPlayer) {
        if (currentPlayer.equals("Black")) {
            this.currentPlayer.setText("White");
        }
        else {
            this.currentPlayer.setText("Black");
        }
    }

}
