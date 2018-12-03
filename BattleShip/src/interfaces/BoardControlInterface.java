/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exception.InvalidCommandException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Till
 */
public interface BoardControlInterface {
    public void readInput(InputStreamReader isr, BufferedReader br) throws InvalidCommandException, IllegalArgumentException;
    
    public boolean getGameOver();
}
