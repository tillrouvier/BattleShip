
import enums.Direction;
import game.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Till
 */
public class ImplementationTests {

    public static void main(String[] args) {
        /*
            . = NOTHING
            ~ = WATER
            O = SHIP
            X = HIT    
         */
        FriendlyField ff = new FriendlyField();
        EnnemyField ef = new EnnemyField();

        try {
            ff.setShipOnBoard(0, 0, 9, Direction.LEFT); //this should work
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }
        try {
            ff.setShipOnBoard(1, 1, 1, Direction.DOWN); //this should not work
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }
        try {
            ff.setShipOnBoard(1, 1, 1, Direction.RIGHT); //this should not work
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }
        try {
            ff.setShipOnBoard(1, 1, 2, Direction.RIGHT); //this should work
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }
        try {
            ff.setShipOnBoard(1, -1, 0, Direction.DOWN); //this should not work
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }

        try {
            ff.shot(2, 0); //this should work and shoot on the first ship we placed
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("OutOfBounds\n");
        }
        try {
            ff.shot(0, 0); //this should work but not do anything because it is water
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("OutOfBounds\n");
        }
        try {
            ff.shot(-1, 10); //this should not work
            ff.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("OutOfBounds\n");
        }

        try {
            ef.shot(2, 0, ff); //this should work and transfer the data from the friendlyfield to the ennemyfield of these coordinates, which is a hit
            ef.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }
        try {
            ef.shot(5, 5, ff); //this should work and transfer the data from the friendlyfield to the ennemyfield of these coordinates, which is water
            ef.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }
        try {
            ef.shot(-1, 10, ff); //this should not work
            ef.displayBoard();
        } catch (IllegalArgumentException ex) {
            System.out.println("IllegalArgument\n");
        }
        
        //What still remains to do would be the game itself, how it would end and begin

    }
}
