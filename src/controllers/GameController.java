package controllers;

import models.*;
import strategies.RowWinningStrategy;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import strategies.WinningStrategy;

public class GameController {
    private final BoardController boardController;

    public GameController(BoardController boardController) {
        this.boardController = boardController;
    }

    public Game startGame(){
        System.out.println("Starting game...");
        System.out.println("what size of the board?");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Board board = new Board(size);
        System.out.println("Number of Players? (including bots - if any)");
        int numPlayers = sc.nextInt();

        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": ");
            String playerName = sc.next();
            System.out.println("Symbol?");
            Character symnbol = sc.next().charAt(0);
            System.out.println("Is player is bot? (Y/N");
            Character isBot = sc.next().charAt(0);
            PlayerType playerType= isBot== 'Y'? PlayerType.BOT: PlayerType.HUMAN;
            if(playerType.equals(PlayerType.BOT)){
                System.out.println("please enter level of the bot:");
                int level = sc.nextInt();
                Level botLevel = null;
                switch(level){
                    case 1: {
                        botLevel=Level.EASY;
                        break;
                    }
                    case 2: {
                        botLevel=Level.MEDIUM;
                        break;
                    }
                    default: botLevel=Level.HARD;
                }
                playerList.add(new Botplayer(playerName,symnbol,i,playerType,botLevel));
            }else{
                playerList.add(new Player(playerName,symnbol,i,playerType));    
            }
            

        }
        return Game.getBuilder().setDimension(size).setPlayers(playerList).build();

    }

    public GameState checkState(Game game) {
        return  game.getGameState();

    }

    public void printBoard(Game game) {

        game.getBoard().display();

    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void makeMove(Game game) {
        boolean isEmpty =checkAtleastOneEmptySpace(game.getBoard());
        if(!isEmpty){
            game.setGameState(GameState.DRAW);
            return;
        }
        Player currentPlayer = game.getPlayers().get(game.getNextPlayerIndex());
        System.out.printf("Its player %s",currentPlayer.getName());
        //logic for the palyer to make a move
        //check winning stategies

        // get the move for the player (bot/Move)
        Move proposedMove = currentPlayer.makeMove(game.getBoard());

        //2.placing the move on the board
        boardController.applyMove(proposedMove, game.getBoard());
        game.getMoves().add(proposedMove);
        if(checkWin(game.getWinningStrategies(),game.getBoard(), proposedMove)){
            game.setGameState(GameState.WIN);
            game.setWinner(currentPlayer);
        }

        game.setNextPlayerIndex((game.getNextPlayerIndex()+1) % game.getPlayers().size());
    }

    private boolean checkAtleastOneEmptySpace(Board board) {
        int rows=board.getCells().size();
        for(int i=0;i<rows;i++){
            for (int j=0;j<rows;j++){
                if(board.getCells().get(i).get(j).getCellState().equals(CellState.FREE)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkWin(List<WinningStrategy> winningStrategies, Board board,Move move){
        for(WinningStrategy ws : winningStrategies){
            if(ws.checkWin(board,move)){
                return true;
            }
        }
        return false;
    }

    public void checkundo(Game game) {
        System.out.println("would you like to undo the last move");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (input.equals("Y")) {
            Move move = game.getMoves().remove(game.getMoves().size() - 1);
            int n= game.getPlayers().size();
            game.setNextPlayerIndex(((game.getNextPlayerIndex()-1) + n)%n);
            int row =move.getCell().getRow();
            int col =move.getCell().getCol();
            Cell cell=game.getBoard().getCells().get(row).get(col);
            cell.setCellState(CellState.FREE);
            cell.setPlayer(null);
            Map<Player,Integer> np=game.getBoard().getRowsMapping().get(row);
            np.put(move.getCell().getPlayer(), np.get(move.getCell().getPlayer())-1);

            Map<Player,Integer> np2=game.getBoard().getRowsMapping().get(row);
            np2.put(move.getCell().getPlayer(), np2.get(move.getCell().getPlayer())-1);
        }

    }
}
