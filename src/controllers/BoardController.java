package controllers;

import models.*;

public class BoardController {


    public void applyMove(Move move, Board board) {
        //valid move
        // update the cell
        //check winner and change state

        int row= move.getCell().getRow();
        int col= move.getCell().getCol();

        if(!(row>=0 && col>=0 && row<board.getCells().size() &&col < board.getCells().size())){
            throw new IllegalArgumentException("Outside of bounds");
        }
        if(!board.getCells().get(row).get(col).getCellState().equals(CellState.FREE)){
            throw new IllegalArgumentException(("Cell is not free"));
        }
        Player player = move.getCell().getPlayer();
        Cell cell = board.getCells().get(row).get(col);
        cell.setCellState(CellState.OCCUPIED);
        cell.setPlayer(move.getCell().getPlayer());

        int countRows= board.getRowsMapping().get(row).getOrDefault(player,0);
        board.getRowsMapping().get(row).put(player,countRows);

        int countCols= board.getRowsMapping().get(col).getOrDefault(player,0);
        board.getColMapping().get(row).put(player,countCols);


    }
}
