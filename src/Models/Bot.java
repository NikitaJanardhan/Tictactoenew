package Models;

import Strategy.BotPlayingStrategy;
import factories.BotPlayingStrategyFactory;

public class Bot extends  Player{
    private  DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;


    public Bot(Character symbol, String name, int id, PlayerType playerType, DifficultyLevel difficultyLevel) {
        super(symbol, name, id, playerType);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingstrategy(difficultyLevel);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println("bot is making the move");
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;



    }
}
