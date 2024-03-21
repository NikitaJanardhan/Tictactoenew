package Strategy;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningstrategy implements  WinningStrategy {

    Map<Character,Integer>leftDiaMap= new HashMap<>();
    Map<Character,Integer>rightDiaMap= new HashMap<>();


    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col=move.getCell().getCol();

        Character symbol = move.getPlayer().getSymbol();

        // if the move was the part of left diagonal
        if(row==col){
            // check if we r puuting symbol first time
            if (!leftDiaMap.containsKey(symbol)){
                leftDiaMap.put(symbol,0);
            }
            leftDiaMap.put(symbol,leftDiaMap.get(symbol)+1);

            // check if entire diagonal filled with this symbol

            if (leftDiaMap.get(symbol).equals(board.getDimensions())){
                return true;
            }


        }
        if (row+col==(board.getDimensions()-1)){
            // check if we r putting this symbol for the first time in this diagonal

            if (!rightDiaMap.containsKey(symbol)){
                rightDiaMap.put(symbol,0);
            }
            rightDiaMap.put(symbol,rightDiaMap.get(symbol)+1);

            // check if entire diagonal filled with this symbol
            if (rightDiaMap.get(symbol).equals(board.getDimensions())){
                return true;
            }

        }
        return false;
    }

    @Override
    public void handleundo(Board board, Move lastMove) {
        Character symbol = lastMove.getPlayer().getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        //check if the move was part of left diagonal

        if (row==col){
            leftDiaMap.put(symbol,leftDiaMap.get(symbol)-1);
        }
        if (row+col==(board.getDimensions()-1)){
            rightDiaMap.put(symbol,rightDiaMap.get(symbol)-1);
        }


    }
}
