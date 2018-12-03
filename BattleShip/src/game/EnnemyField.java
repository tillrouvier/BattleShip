package game;

import enums.FieldStatus;
import interfaces.EnnemyFieldInterface;

/**
 *
 * @author Till
 */
public class EnnemyField extends Field implements EnnemyFieldInterface {

    public EnnemyField() {
        super(FieldStatus.NOTHING);
    }

    @Override
    public void shot(int line, int column, FriendlyField fs) throws IllegalArgumentException {
        if (isInBound(line, column)) {
            if (fs.board[line][column] == FieldStatus.SHIP || fs.board[line][column] == FieldStatus.HIT) {
                board[line][column] = FieldStatus.HIT;
            } else if (fs.board[line][column] == FieldStatus.WATER) {
                board[line][column] = FieldStatus.WATER;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

}
