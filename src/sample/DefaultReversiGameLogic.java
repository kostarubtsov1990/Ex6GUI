package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostarubtsov1990 on 09/01/18.
 */
public class DefaultReversiGameLogic extends GameLogic{

    List<Cell> xsLocation;
    List<Cell> osLocation;

    DefaultReversiGameLogic () {
        super.possibleMoves = new ArrayList<Cell>();
        xsLocation = new ArrayList<Cell>();
        osLocation = new ArrayList<Cell>();
    }

    public native void CheckPossibleMoves(final Board board, ReversiGame.player player, Board.symbol playerSymbol);
    public native void UpdateBoard(final Board board, Cell coordinate, ReversiGame.player player);
    public native boolean CheckLocation(Cell coordinate);
    public native boolean IsGameOver(final Board board);
    public native Board.symbol DeclareWinner (final Board board);

}
