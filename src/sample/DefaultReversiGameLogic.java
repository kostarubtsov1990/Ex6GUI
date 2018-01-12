package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostarubtsov1990 on 09/01/18.
 */
public class DefaultReversiGameLogic extends GameLogic{


    private long self_ptr;

    DefaultReversiGameLogic () {
        initialize ();
    }

    private native void initialize ();
    public native void CheckPossibleMoves(int [][] board, int player);
    public native int[][] UpdateBoard(int [][] board, int x, int y, int player);
    public native boolean CheckLocation(int x, int y);
    public native boolean IsGameOver(int [][] board);
    public native int DeclareWinner (int [][] board);
    public native boolean IsPossibleMoveExist ();

    private native void destroy ();

}