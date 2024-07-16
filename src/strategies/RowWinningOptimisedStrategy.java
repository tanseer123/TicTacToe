package strategies;

import models.Board;
import models.Move;

public class RowWinningOptimisedStrategy implements WinningStrategy{
    @Override
    public boolean checkWin(Board board, Move move) {
        int row= move.getCell().getRow();
        return board.getRowsMapping().get(row).get(move.getCell().getPlayer())
                == board.getCells().size();

    }
}
