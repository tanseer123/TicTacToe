package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@Data
@AllArgsConstructor
public class Player {
    private String name;
    private Character symbol;
    private int id;
    private PlayerType playerType;

    public Move makeMove(Board board){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Row: ");
        int row = scanner.nextInt();
        System.out.println("Column: ");
        int column = scanner.nextInt();
        Cell cell = new Cell(row, column, this,CellState.OCCUPIED);
        return new Move(cell);

    }



}
