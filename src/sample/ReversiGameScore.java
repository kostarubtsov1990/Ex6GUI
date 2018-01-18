package sample;

/**
 * Created by kostarubtsov1990 on 16/01/18.
 */
public class ReversiGameScore extends Score{

    private int xScore;
    private int oScore;

    public ReversiGameScore() {
        xScore = 0;
        oScore = 0;
    }

    public int GetXScore() {
        return xScore;
    }

    public int GetOScore() {
        return oScore;
    }

    public void UpdateScore (Board.symbol [][] boardContent) {
        int rows = boardContent.length;
        int cols = boardContent[0].length;
        xScore = 0;
        oScore = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (boardContent[i][j] == Board.symbol.xSymbol) {
                    xScore++;
                }
                else if (boardContent[i][j] == Board.symbol.oSymbol) {
                    oScore++;
                }
            }
        }
    }
}
