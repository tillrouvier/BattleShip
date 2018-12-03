/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import enums.Direction;
import interfaces.FriendlyFieldInterface;

/**
 *
 * @author Till
 */
public class FriendlyFieldMock extends FieldMock implements FriendlyFieldInterface{

    @Override
    public void setShipOnBoard(int shipIndex, int line, int column, Direction dir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean shot(int line, int column) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
