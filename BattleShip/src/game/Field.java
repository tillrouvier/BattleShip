package game;

import enums.FieldStatus;
import interfaces.FieldInterface;

/**
 *
 * @author Till
 */
public class Field implements FieldInterface {

    protected FieldStatus[][] board = new FieldStatus[FieldInterface.BOARD_HEIGHT][FieldInterface.BOARD_WIDTH];

    public Field(FieldStatus fs) {
        for (FieldStatus[] board1 : board) {
            for (int i = 0; i < board1.length; i++) {
                board1[i] = fs;
            }
        }
    }

    public FieldStatus[][] getBoard() {
        return board;
    }

    public void setBoardValueAtIndexes(int line, int column, FieldStatus fs) {
        board[line][column] = fs;
    }

    @Override
    public void displayBoard() {
        System.out.println("");
        char charLine = 'A';
        System.out.print("  ");
        for(int i=0; i<10;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for (FieldStatus[] board1 : board) {
            System.out.print((charLine++)+" ");
            for (FieldStatus board2 : board1) {
                if (null != board2) {
                    switch (board2) {
                        case WATER:
                            System.out.print("~ ");
                            break;
                        case NOTHING:
                            System.out.print(". ");
                            break;
                        case HIT:
                            System.out.print("X ");
                            break;
                        case SHIP:
                            System.out.print("O ");
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    protected boolean isInBound(int line, int column) {
        return (line >= 0) && (line < FieldInterface.BOARD_HEIGHT) && (column >= 0) && (column < FieldInterface.BOARD_WIDTH);
    }
}
