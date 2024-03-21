package Strategy;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColoumnWinningStrategy implements WinningStrategy {

    Map<Integer, Map<Character,Integer> > colMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Character symbol=move.getPlayer().getSymbol();

        if (!colMap.containsKey(col)){
            colMap.put(col,new HashMap<>());
        }
        Map<Character,Integer>colMaps=colMap.get(col);
        if(!colMaps.containsKey(symbol)){
            colMaps.put(symbol,0);
        }
        colMaps.put(symbol,colMaps.get(symbol)+1);
        if (colMaps.get(symbol).equals(board.getDimensions())){
            return true;
        }
        return false;

    }

    @Override
    public void handleundo(Board board, Move lastMove) {
        Character symbol = lastMove.getPlayer().getSymbol();
        int col = lastMove.getCell().getCol();
        Map<Character,Integer>colMaps=colMap.get(col);
        colMaps.put(symbol,colMaps.get(symbol)-1);


    }
}
