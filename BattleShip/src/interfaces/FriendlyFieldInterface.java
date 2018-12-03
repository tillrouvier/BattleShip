/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import enums.Direction;

/**
 *
 * @author Till
 */
public interface FriendlyFieldInterface extends FieldInterface {

    public void setShipOnBoard(int shipIndex, int line, int column, Direction dir) throws IllegalArgumentException;

    public boolean shot(int line, int column) throws IllegalArgumentException;
}
