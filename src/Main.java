import controllers.BoardController;
import controllers.GameController;
import models.Board;
import models.Game;
import models.GameState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GameController gameController = new GameController(new BoardController());
        Game game=gameController.startGame();
        System.out.println("Welcome to TicTacToe!");
        while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            //print board
            //which player turn
            //make a move
            gameController.printBoard(game);
            gameController.makeMove(game);
            gameController.checkundo(game);
        }

        if(gameController.checkState(game).equals(GameState.DRAW)){
            System.out.println("the game ended in Draw!");
        }
        else{
            System.out.printf("the player %s won.",gameController.getWinner(game).getName());
        }

        //Board board = new Board(3);
        //board.display();
    }
}