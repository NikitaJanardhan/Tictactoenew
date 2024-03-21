package Strategy;

import Models.Board;
import Models.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board,Move move);


    void handleundo(Board board, Move lastMove);
}
