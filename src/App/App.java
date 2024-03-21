package App;

import Controller.GameController;
import Models.*;
import Strategy.ColoumnWinningStrategy;
import Strategy.DiagonalWinningstrategy;
import Strategy.RowWinningStrategy;
import Strategy.WinningStrategy;
import exception.DuplicateSymbolForPlayer;
import exception.MorethanOneBotException;
import exception.PlayerAndBoardCountMismatch;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws PlayerAndBoardCountMismatch, MorethanOneBotException, DuplicateSymbolForPlayer {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int dimension = 3;
        ArrayList< Player > players = new ArrayList<>();
        players.add(new HumanPlayer('x', "Nikita", 1, PlayerType.HUMAN,scanner));
        //players.add(new Bot('0', "GPT", 2, PlayerType.BOT, DifficultyLevel.EASY));
        players.add(new HumanPlayer('0', "Avi", 2, PlayerType.HUMAN,scanner));


        ArrayList< WinningStrategy > winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColoumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningstrategy());


        Game game = gameController.createGame(dimension, players, winningStrategies);

        while (Gamestate.IN_PROG.equals(game.getGamestate())) {
            gameController.printBoard(game);

            // if somebody wants to move on undo
            System.out.println("Does anyone want to undo ?(y/n");
            String undo = scanner.next();
            if (undo.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;


            }
            gameController.makeMove(game);


        }
        if (Gamestate.CONCLUDED.equals(game.getGamestate())) {
            System.out.println(game.getWinner().getName()+" was won the game");


        }
        if (Gamestate.DRAW.equals(game.getGamestate())){
            System.out.println("It is draw");
        }

    }
}
