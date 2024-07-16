package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Board {
    private List<List<Cell>> cells;
    private List<Map<Player,Integer>> rowsMapping = new ArrayList();
    private List<Map<Player,Integer>> colMapping= new ArrayList();

    public Board(int dimension) {
        this.cells = new ArrayList<>();
        //[]
        int count=0;

        for (int row = 0; row < dimension; row++) {
            this.cells.add(new ArrayList<>());
            for (int i = 0; i < dimension; i++) {
                this.rowsMapping.add(new HashMap<>());
                this.colMapping.add(new HashMap<>());

            }
            //[
            // [(0,0),(0,1)(0,2],
            //[(1,0),(1,1),(1,2)],
            // ]
            for(int col = 0; col < dimension; col++) {
                this.cells.get(row).add(new Cell(row,col));
                count+=1;
            }
        }
    }

    public void display(){

        for(int row = 0; row < this.cells.size(); row++) {
            for (int col = 0; col < this.cells.size(); col++) {
                if(cells.get(row).get(col).getCellState().equals((CellState.FREE))){
                    System.out.print("|   |");
                }
                else{
                    System.out.printf("| %s |",cells.get(row).get(col).getPlayer().getSymbol());
                }
            }
            System.out.println();
        }

    }



}
