package sample;

public class Context {
    private String player;

    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    public String GetPlayer () {
        return player;
    }

    public void SetPlayer (String playerInfo) {
        player = playerInfo;
    }
}
