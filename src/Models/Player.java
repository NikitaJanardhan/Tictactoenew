package Models;

public abstract class Player {
    private Character Symbol;
    private String Name;
    private int id;
    private PlayerType playerType;
    public abstract Cell makeMove(Board board);


    public Player(Character symbol, String name, int id, PlayerType playerType) {
        this.Symbol = symbol;
        this.Name = name;
        this.id = id;
        this.playerType = playerType;
    }

    public Character getSymbol() {
        return Symbol;
    }

    public void setSymbol(Character symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
