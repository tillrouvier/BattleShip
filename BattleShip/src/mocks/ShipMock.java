/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import interfaces.ShipInterface;

/**
 *
 * @author Till
 */
public class ShipMock implements ShipInterface{

    @Override
    public int getShipValueAtIndex(int index) throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decrementShipValueAtIndex(int index) throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
