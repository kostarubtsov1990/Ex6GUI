package sample;

/**
 * Created by kostarubtsov1990 on 16/01/18.
 */
public abstract class Score {

    public abstract void UpdateScore (Board.symbol [][] boardContent);
    public abstract int GetXScore();
    public abstract int GetOScore();

}
