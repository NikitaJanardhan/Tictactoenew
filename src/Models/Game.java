package Models;

import Strategy.WinningStrategy;
import exception.DuplicateSymbolForPlayer;
import exception.MorethanOneBotException;
import exception.PlayerAndBoardCountMismatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> playerList;
    private Board board;
    private Player winner;
    private Gamestate gamestate;
    private int nextPlayerIndex;
    private List<Move> Moves;
    private List< WinningStrategy > winningStrategies;

    private Game(List<Player> playerList,int dimensions,
                 List<WinningStrategy> winningStrategies) {
        this.nextPlayerIndex = 0;
        this.gamestate = Gamestate.IN_PROG;
        this.Moves = new ArrayList<>();
        this.playerList = playerList;
        this.board = new Board(dimensions);
        this.winningStrategies=winningStrategies;
    }

    public Game(List< Player > playerList) {

        this.playerList = playerList;
    }

    public List< Player > getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List< Player > playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Gamestate getGamestate() {
        return gamestate;
    }

    public void setGamestate(Gamestate gamestate) {
        this.gamestate = gamestate;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List< Move > getMoves() {
        return Moves;
    }

    public void setMoves(List< Move > moves) {
        Moves = moves;
    }


   public static Builder getBuilder(){
        return new Builder();

   }

    public void printBoard() {
        board.printBoard();

    }

    public void makeMove() {
        Player player = playerList.get(nextPlayerIndex);
        Cell cell = player.makeMove(board);

        Move move = new Move(cell, player);
        Moves.add(move);

        if(checkWinner(move, board)){
            gamestate = gamestate.CONCLUDED;
            winner = player;
            return ;
        }

        if(Moves.size()==board.getDimensions()*board.getDimensions()){
            gamestate = Gamestate.DRAW;
            return ;
        }
        nextPlayerIndex++;
        nextPlayerIndex =nextPlayerIndex % playerList.size();



    }

    private boolean checkWinner(Move move, Board board) {
       for (WinningStrategy winningStrategy: winningStrategies ){
           if (winningStrategy.checkWinner(board,move)){
               return true;
           }
       }
       return false;
    }

    public void undo() {
        if (Moves.size()==0){
            System.out.println("No moves tp undo");
            return;
        }
        Move lastMove = Moves.get(Moves.size() - 1);
        Moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);

        for (WinningStrategy winningStrategy :winningStrategies){
            winningStrategy.handleundo(board,lastMove);
        }
        if (nextPlayerIndex!=0) {
            nextPlayerIndex--;
        }
        else {
            nextPlayerIndex=playerList.size();
        }



    }

    public static class Builder{

        private List<Player> players;

        private int dimension;
        private List<WinningStrategy>winningStrategies;

        private Builder(){
            this.dimension=0;
            this.players=new ArrayList<>();

        }
        public Builder setDimension(int dimension){
            this.dimension=dimension;
            return this;

        }
        public Builder setPlayers(List<Player> players){

            this.players=players;
            return this;
        }
        public  Builder setwinningstrategies(List<WinningStrategy>winningStrategies){
            this.winningStrategies=winningStrategies;
            return this;
        }
        public Game build() throws MorethanOneBotException, PlayerAndBoardCountMismatch, DuplicateSymbolForPlayer {
            //add all the validations
            validateBotCount();
            validateDimensionsAndPlayer();
            validateUniqueSymbolForEachPlayer();
            return new Game(players,dimension,winningStrategies);

        }

       private void validateUniqueSymbolForEachPlayer() throws DuplicateSymbolForPlayer {
           HashSet< Character > symbols = new HashSet<>();
           for(Player player: players){
               if(symbols.contains(player.getSymbol())){
                   throw new DuplicateSymbolForPlayer();
               }
               symbols.add(player.getSymbol());
           }

       }

       private void validateDimensionsAndPlayer() throws PlayerAndBoardCountMismatch {
            if(players.size()!=dimension-1) {
                throw new PlayerAndBoardCountMismatch();

            }

       }

       private void validateBotCount() throws MorethanOneBotException {
            int botCount=0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
                if(botCount>1){
                    throw new MorethanOneBotException();
                }
            }
       }


   }





}
