package game;

import interfaces.ShipInterface;

/**
 *
 * @author Till
 */
public class Ship implements ShipInterface {

    private int[] shipArray = {ShipInterface.CARRIER_SIZE, ShipInterface.BATTLESHIP_SIZE, ShipInterface.CRUISER_SIZE, ShipInterface.SUBMARINE_SIZE, ShipInterface.DESTROYER_SIZE};
    private boolean[] boolArray = new boolean[shipArray.length]; //to know if a ship is set or not
    //this array contains at every index the length of each ship, from the biggest at index 0 to the smallest at index 4

    public Ship(){
        for(boolean b : boolArray){
            b = false;
        }
    }
    
    public int length(){
        return shipArray.length;
    }
    
    @Override
    public int getShipValueAtIndex(int index) throws ArrayIndexOutOfBoundsException { //this returns the length of the ship of index given
        return shipArray[index];
    }
    
    public boolean getBoolValueAtIndex(int index) throws ArrayIndexOutOfBoundsException { //this returns if the ship is placed or not
        return boolArray[index];
    }
    
    public void setBoolValueAtIndex(int index, boolean value) throws ArrayIndexOutOfBoundsException { //changes between if the ship is placed or not
        this.boolArray[index] = value;
    }

    @Override
    public void decrementShipValueAtIndex(int index) throws ArrayIndexOutOfBoundsException { //this decrements the length of the ship at index given by 1, will be useful later to know if the ship is sunk or not
        shipArray[index]--;
    }

    
}
