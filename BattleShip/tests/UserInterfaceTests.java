/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exception.InvalidCommandException;
import game.BoardControl;
import interfaces.BoardControlInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Till
 */
public class UserInterfaceTests {
    /*
        What commands to write to test the game properly:
    
            - HELP
            - PRINT_LOCAL
    
            - SET_SHIP 0 A 0 down
            - set_ship 1 C 4 right
    
            - REMOVE_SHIPS
    
            - SET_SHIP 0 B 1 down
            - SET_SHIP 1 D 3 right
            - SET_SHIP 2 F 8 left
            - SET_SHIP 3 J 9 up
            - SET_SHIP 4 H 2 right
    
            - PRINT_REMOTE
    
            - SHOT B 1
            - shot C 1
            - SHOT D 1
            - SHOT E 1
            - SHOT F 1
    
            - SHOT D 3
            - SHOT D 4
            - SHOT D 5
            - SHOT D 6
    
            - SHOT F 6
            - SHOT F 7
            - SHOT F 8
            
            - SHOT J 9
            - SHOT I 9
            - SHOT H 9
    
            - SHOT H 3
            - SHOT H 2
    
    */
    public static void main(String[] args) {
        BoardControlInterface bc = new BoardControl();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("WELCOME TO THE BATTLESHIP GAME!\nThe game is still work in progress, so you can not play against another player. Have fun nonetheless!");
        System.out.println("You can type 'HELP' for a list of commands.\n");
        System.out.println("Begin by placing your ships.\n");
        while (!bc.getGameOver()) { //while loop ends when game ends
            try {
                bc.readInput(isr, br);
            } catch (InvalidCommandException ex) {
                System.out.println("ERROR: Invalid command, type 'HELP' for a list of commands.");
                System.out.println("Remember: you can not shoot when all ships are not placed, and you can not place new ships when all ships are already set.\n");
            } catch (IllegalArgumentException ex) {
                System.out.println("ERROR: You can not choose those parameters!");
                System.out.println("\t-> Either you wanted to place a ship or to shoot outside of the board;");
                System.out.println("\t-> To place a ship you already placed;");
                System.out.println("\t-> Or to shoot somewhere you already shot;");
                System.out.println("Try to display the board with 'PRINT_LOCAL' or 'PRINT_REMOTE' to see what you can do.\n");
            }
        }
    }
}
