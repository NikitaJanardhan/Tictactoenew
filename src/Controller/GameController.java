package Controller;

import Models.Game;
import Models.Player;
import Strategy.WinningStrategy;
import exception.DuplicateSymbolForPlayer;
import exception.MorethanOneBotException;
import exception.PlayerAndBoardCountMismatch;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> PlayerList,
                           List< WinningStrategy > winningStrategiesList) throws PlayerAndBoardCountMismatch, MorethanOneBotException, DuplicateSymbolForPlayer {
        return Game.getBuilder()
                .setPlayers(PlayerList)
                .setDimension(dimension)
                .setwinningstrategies(winningStrategiesList)
                .build();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void printBoard(Game game){

        game.printBoard();

    }


    public void undo(Game game) {
        game.undo();

    }
}
