/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Till
 */
public interface ShipInterface {

    public static int CARRIER_SIZE = 5;
    public static int BATTLESHIP_SIZE = 4;
    public static int CRUISER_SIZE = 3;
    public static int SUBMARINE_SIZE = 3;
    public static int DESTROYER_SIZE = 2;

    public int getShipValueAtIndex(int index) throws ArrayIndexOutOfBoundsException;

    public void decrementShipValueAtIndex(int index) throws ArrayIndexOutOfBoundsException;
}
