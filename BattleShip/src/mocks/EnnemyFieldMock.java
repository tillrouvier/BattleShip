/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import game.FriendlyField;
import interfaces.EnnemyFieldInterface;

/**
 *
 * @author Till
 */
public class EnnemyFieldMock extends FieldMock implements EnnemyFieldInterface {

    @Override
    public void shot(int line, int column, FriendlyField fs) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
