package sample;

/**
 * Created by kostarubtsov1990 on 09/01/18.
 */
public class ReversiGame {
    enum player {firstPlayer, secondPlayer}

    private Board board;
    private GameLogic logic;

    ReversiGame (Board board, GameLogic logic) {
        this.board = board;
        this.logic = logic;
    }

    public GameLogic GetGameLogic() {
        return logic;
    }

    public Board GetBoard() {
        return board;
    }

}
