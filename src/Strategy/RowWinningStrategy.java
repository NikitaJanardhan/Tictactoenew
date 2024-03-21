package Strategy;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {

    Map<Integer,Map<Character,Integer>> rowMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Character symbol=move.getPlayer().getSymbol();

        if (!rowMap.containsKey(row)){
            rowMap.put(row,new HashMap<>());
        }
       Map<Character,Integer>rowMaps=rowMap.get(row);
       if(!rowMaps.containsKey(symbol)){
       rowMaps.put(symbol,0);
       }
       rowMaps.put(symbol,rowMaps.get(symbol)+1);
       if (rowMaps.get(symbol).equals(board.getDimensions())){
           return true;
       }
   return false;
}

    @Override
    public void handleundo(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        Character symbol = lastMove.getPlayer().getSymbol();
        Map<Character,Integer> rowMaps=rowMap.get(row);
        rowMaps.put(symbol,rowMaps.get(symbol)-1);

    }
}
