package strategies;

import models.Board;
import models.CellState;
import models.Move;

public class RowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWin(Board board, Move move) {
        // The row in which player played the move.
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        int rows=board.getCells().size();
        // Iterate all the col indices for that row and check
        // all the cells are occupied by the same player.

        int cnt=0;
        for(int i=0;i<rows;i++){
            if(board.getCells().get(row).get(i).getCellState()
                    != CellState.OCCUPIED ||
                    board.getCells().get(row).get(i).getPlayer()
                            != move.getCell().getPlayer()){

                return false;

            }

        }
        return true;
    }

}