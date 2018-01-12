package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostarubtsov1990 on 09/01/18.
 */
public abstract class GameLogic {


    public abstract void CheckPossibleMoves(int [][] board, int player);
    public abstract int[][] UpdateBoard(int [][] board, int x, int y, int player);
    public abstract boolean CheckLocation(int x, int y);
    public abstract boolean IsGameOver(int [][] board);
    public abstract int DeclareWinner (int [][] board);
    public abstract boolean IsPossibleMoveExist ();
}
