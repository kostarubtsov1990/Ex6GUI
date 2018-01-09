package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostarubtsov1990 on 09/01/18.
 */
public abstract class GameLogic {

    List<Cell> possibleMoves;

    public abstract void CheckPossibleMoves(final Board board, ReversiGame.player player, Board.symbol playerSymbol);
    public abstract void UpdateBoard(final Board board, Cell coordinate, ReversiGame.player player);
    public List <Cell> getPossibleMoves() {
        return possibleMoves;
    }
    public abstract boolean CheckLocation(Cell coordinate);
    public abstract boolean IsGameOver(final Board board);
    public abstract Board.symbol DeclareWinner (final Board board);
}
