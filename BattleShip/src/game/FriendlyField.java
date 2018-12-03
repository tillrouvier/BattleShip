package game;

import enums.*;
import interfaces.FieldInterface;
import interfaces.FriendlyFieldInterface;

/**
 *
 * @author Till
 */
public class FriendlyField extends Field implements FriendlyFieldInterface {

    private int[][] logicalBoard = new int[FieldInterface.BOARD_HEIGHT][FieldInterface.BOARD_WIDTH]; //will contain informations about the different ships more precisely, but is not useful to the player
    private Ship ships = new Ship();

    public FriendlyField() {
        super(FieldStatus.WATER);
        for (int[] logicalBoard1 : logicalBoard) {
            for (int j = 0; j < logicalBoard1.length; j++) {
                logicalBoard1[j] = -1;
            }
        }
    }

    public Ship getShips() {
        return ships;
    }

    private boolean areAllSidesWater(int line, int column) { //very repetitive, probably exists an other way
        boolean res = (board[line][column] == FieldStatus.WATER);
        if (res && column == 0 && line == 0) {
            res = ((board[line + 1][column] == FieldStatus.WATER) && (board[line][column + 1] == FieldStatus.WATER)) && res;

        } else if (res && column == FieldInterface.BOARD_WIDTH - 1 && line == FieldInterface.BOARD_HEIGHT - 1) {
            res = ((board[line - 1][column] == FieldStatus.WATER) && (board[line][column - 1] == FieldStatus.WATER)) && res;

        } else if (res && column == FieldInterface.BOARD_WIDTH - 1 && line == 0) {
            res = ((board[line + 1][column] == FieldStatus.WATER) && (board[line][column - 1] == FieldStatus.WATER)) && res;

        } else if (res && column == 0 && line == FieldInterface.BOARD_HEIGHT - 1) {
            res = ((board[line - 1][column] == FieldStatus.WATER) && (board[line][column + 1] == FieldStatus.WATER)) && res;

        } else if (res && line == 0) {
            res = ((board[line + 1][column] == FieldStatus.WATER) && (board[line][column - 1] == FieldStatus.WATER) && (board[line][column + 1] == FieldStatus.WATER)) && res;

        } else if (res && line == FieldInterface.BOARD_HEIGHT - 1) {
            res = ((board[line - 1][column] == FieldStatus.WATER) && (board[line][column - 1] == FieldStatus.WATER) && (board[line][column + 1] == FieldStatus.WATER)) && res;

        } else if (res && column == 0) {
            res = ((board[line - 1][column] == FieldStatus.WATER) && (board[line + 1][column] == FieldStatus.WATER) && (board[line][column + 1] == FieldStatus.WATER)) && res;

        } else if (res && column == FieldInterface.BOARD_WIDTH - 1) {
            res = ((board[line - 1][column] == FieldStatus.WATER) && (board[line + 1][column] == FieldStatus.WATER) && (board[line][column - 1] == FieldStatus.WATER)) && res;

        } else {
            res = ((board[line - 1][column] == FieldStatus.WATER) && (board[line + 1][column] == FieldStatus.WATER) && (board[line][column - 1] == FieldStatus.WATER)
                    && (board[line][column + 1] == FieldStatus.WATER)) && res;
        }
        return res;
    }

    @Override
    public void setShipOnBoard(int shipIndex, int line, int column, Direction dir) throws IllegalArgumentException {
        if (!ships.getBoolValueAtIndex(shipIndex)) {

            int dirx = 0, diry = 0;

            if (null != dir) {
                switch (dir) {
                    case UP:
                        diry = -1;
                        break;
                    case DOWN:
                        diry = 1;
                        break;
                    case LEFT:
                        dirx = -1;
                        break;
                    case RIGHT:
                        dirx = 1;
                        break;
                    default:
                        break;
                }
            }

            if (isInBound(line, column) && isInBound(line + ships.getShipValueAtIndex(shipIndex) * diry, column + ships.getShipValueAtIndex(shipIndex) * dirx)) {
                int l = line;
                int c = column;
                int cmt = ships.getShipValueAtIndex(shipIndex);

                if (dirx == 1 || diry == 1) {
                    while (((l <= line + (ships.getShipValueAtIndex(shipIndex) - 1)) && (c <= column + (ships.getShipValueAtIndex(shipIndex) - 1)))
                            && (areAllSidesWater(l, c))) {
                        l += diry;
                        c += dirx;
                        cmt--;
                    }
                    if (cmt <= 0) {
                        for (l = line; l <= line + (ships.getShipValueAtIndex(shipIndex) - 1) * diry; l++) {
                            for (c = column; c <= column + (ships.getShipValueAtIndex(shipIndex) - 1) * dirx; c++) {
                                logicalBoard[l][c] = shipIndex;
                                board[l][c] = FieldStatus.SHIP;
                            }
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    while (((l >= line + (ships.getShipValueAtIndex(shipIndex) - 1) * diry) && (c >= column + (ships.getShipValueAtIndex(shipIndex) - 1) * dirx))
                            && (areAllSidesWater(l, c))) {
                        l += diry;
                        c += dirx;
                        cmt--;
                    }
                    if (cmt <= 0) {
                        for (l = line; l >= line + (ships.getShipValueAtIndex(shipIndex) - 1) * diry; l--) {
                            for (c = column; c >= column + (ships.getShipValueAtIndex(shipIndex) - 1) * dirx; c--) {
                                logicalBoard[l][c] = shipIndex;
                                board[l][c] = FieldStatus.SHIP;
                            }
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                }

            } else {
                throw new IllegalArgumentException();
            }
            ships.setBoolValueAtIndex(shipIndex, true);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean shot(int line, int column) throws IllegalArgumentException {
        boolean res = false;
        if (isInBound(line, column) && board[line][column] != FieldStatus.HIT) {
            if (board[line][column] == FieldStatus.SHIP) {
                res = true;
                board[line][column] = FieldStatus.HIT;
                try {
                    ships.decrementShipValueAtIndex(logicalBoard[line][column]);
                } catch (ArrayIndexOutOfBoundsException a) {
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return res;
    }

    public boolean allShipsArePlaced() {
        return ships.getBoolValueAtIndex(0) && ships.getBoolValueAtIndex(1) && ships.getBoolValueAtIndex(2) && ships.getBoolValueAtIndex(3) && ships.getBoolValueAtIndex(4);
    }

    public int getLogicalValueAtIndex(int line, int column) {
        return logicalBoard[line][column];
    }

    public boolean allShipsAreSunk() {
        return ships.getShipValueAtIndex(0) == 0 && ships.getShipValueAtIndex(1) == 0 && ships.getShipValueAtIndex(2) == 0 && ships.getShipValueAtIndex(3) == 0 && ships.getShipValueAtIndex(4) == 0;
    }

}
