/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import game.FriendlyField;

/**
 *
 * @author Till
 */
public interface EnnemyFieldInterface extends FieldInterface {
    public void shot(int line, int column, FriendlyField fs) throws IllegalArgumentException;
}
