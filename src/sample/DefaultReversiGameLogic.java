package sample;

import sun.plugin2.util.SystemUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostarubtsov1990 on 09/01/18.
 */
public class DefaultReversiGameLogic extends GameLogic{



    private long self_ptr;
    private int gameSize;

    static {
        String OS = System.getProperty("os.name");
        String libraryName;
        if (OS.contains("Linux")) {
            libraryName = "libJNIforEx6GUI.so";
        }
        else {
            libraryName = "libJNIforEx6GUI.dll";
        }
        File libraryFile = new File(libraryName);
        try (InputStream is = DefaultReversiGameLogic.class.getResourceAsStream(libraryName)) {
        Files.copy(is, libraryFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {}
        //String testing = libraryFile.getAbsolutePath();
            System.load(libraryFile.getAbsolutePath());
        }

    DefaultReversiGameLogic () {
        initialize ();
        super.score = new ReversiGameScore();
    }

    private native void initialize ();
    public native void CheckPossibleMoves(int [][] board, int player);
    public native int[][] UpdateBoard(int [][] board, int x, int y, int player);
    public native boolean CheckLocation(int x, int y);
    public native boolean IsGameOver(int [][] board);
    public native int DeclareWinner (int [][] board);
    public native boolean IsPossibleMoveExist ();
    public void UpdateScore(Board.symbol [][] boardContent) {
        score.UpdateScore(boardContent);
    }
    public void SetGameSize(int gameSize) {
        this.gameSize = gameSize;
    }
    private native void destroy ();

}
