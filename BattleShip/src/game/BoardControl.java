package game;

import enums.Direction;
import exception.InvalidCommandException;
import interfaces.BoardControlInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Till
 */
public class BoardControl implements BoardControlInterface{

    private FriendlyField ff;
    private EnnemyField ef;
    private boolean gameOver;  //to know if the game is over or not

    public BoardControl(FriendlyField ff, EnnemyField ef) { //in case we would like to resume a game from a save
        this.ff = ff;
        this.ef = ef;
        gameOver = false;
    }

    public BoardControl() {
        this(new FriendlyField(), new EnnemyField());
    }

    @Override
    public void readInput(InputStreamReader isr, BufferedReader br) throws InvalidCommandException, IllegalArgumentException {
        System.out.print("commandInput:$ ");
        try {
            //this will read the command input and the first 4 parameters (we never need more than 4)
            String readString = br.readLine();
            readString = readString.trim();

            int spaceIndex = readString.indexOf(' ');
            spaceIndex = spaceIndex != -1 ? spaceIndex : readString.length();
            String commandString = readString.substring(0, spaceIndex);
            String parametersString = readString.substring(spaceIndex);
            parametersString = parametersString.trim();
            
            spaceIndex = parametersString.indexOf(' ');
            spaceIndex = spaceIndex != -1 ? spaceIndex : parametersString.length();
            String firstParameter = parametersString.substring(0, spaceIndex);
            parametersString = parametersString.substring(spaceIndex);
            parametersString = parametersString.trim();
            
            spaceIndex = parametersString.indexOf(' ');
            spaceIndex = spaceIndex != -1 ? spaceIndex : parametersString.length();
            String secondParameter = parametersString.substring(0, spaceIndex);
            parametersString = parametersString.substring(spaceIndex);
            parametersString = parametersString.trim();
            
            spaceIndex = parametersString.indexOf(' ');
            spaceIndex = spaceIndex != -1 ? spaceIndex : parametersString.length();
            String thirdParameter = parametersString.substring(0, spaceIndex);
            parametersString = parametersString.substring(spaceIndex);
            parametersString = parametersString.trim();
            
            spaceIndex = parametersString.indexOf(' ');
            spaceIndex = spaceIndex != -1 ? spaceIndex : parametersString.length();
            String fourthParameter = parametersString.substring(0, spaceIndex);
            parametersString = parametersString.substring(spaceIndex);
            parametersString = parametersString.trim();
            
            if (commandString.toUpperCase().equals("PRINT_LOCAL") && !(ff.allShipsArePlaced()) && firstParameter.isEmpty()) { //can't display local board once all ships are placed
                ff.displayBoard();
            } else if (commandString.toUpperCase().equals("PRINT_REMOTE") && firstParameter.isEmpty()) {
                ef.displayBoard();
            } else if (commandString.toUpperCase().equals("SET_SHIP") && !(ff.allShipsArePlaced()) && parametersString.isEmpty()) { //can't place if all ships are already placed

                int shipIndex, line, column;
                Direction dir;

                if (firstParameter.charAt(0) >= '0' && firstParameter.charAt(0) < '0' + ff.getShips().length()) {
                    shipIndex = firstParameter.charAt(0) - '0'; //read the shipIndex and converts it into an integer

                    if (secondParameter.charAt(0) >= 'A' && secondParameter.charAt(0) <= 'J') {
                        line = secondParameter.charAt(0) - 'A'; //read the line coordinate and converts it into an integer


                        if (thirdParameter.charAt(0) >= '0' && thirdParameter.charAt(0) < '0' + 10) {
                            column = thirdParameter.charAt(0) - '0'; //read the column coordinate and converts it into an integer

                            switch (fourthParameter) { //reads the direction and converts it into a Direction
                                case "up":
                                    dir = Direction.UP;
                                    break;
                                case "down":
                                    dir = Direction.DOWN;
                                    break;
                                case "left":
                                    dir = Direction.LEFT;
                                    break;
                                case "right":
                                    dir = Direction.RIGHT;
                                    break;
                                default:
                                    throw new InvalidCommandException();
                            }

                        } else {
                            throw new IllegalArgumentException();
                        }

                    } else {
                        throw new IllegalArgumentException();
                    }

                } else {
                    throw new IllegalArgumentException();
                }

                ff.setShipOnBoard(shipIndex, line, column, dir);
                ff.displayBoard();
                if (ff.allShipsArePlaced()) {
                    System.out.println("All ships are now set! You can now start to shoot.");
                }

            } else if (commandString.toUpperCase().equals("REMOVE_SHIPS") && !ff.allShipsArePlaced() && firstParameter.isEmpty()) {
                ff = new FriendlyField(); //resets the board. 
                System.out.println("All ships have been removed from the board, you may start to place them again.");
                
            } else if (commandString.toUpperCase().equals("SHOT") && ff.allShipsArePlaced() && thirdParameter.isEmpty()) { //can't shoot if all ships are not placed
                int line, column;
                if (firstParameter.charAt(0) >= 'A' && firstParameter.charAt(0) <= 'J') {
                    line = firstParameter.charAt(0) - 'A';
                    if (secondParameter.charAt(0) >= '0' && secondParameter.charAt(0) <= '9') {
                        column = secondParameter.charAt(0) - '0';
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
                if (ff.shot(line, column)) {
                    System.out.println("A ship was hit!");
                    if (ff.getShips().getShipValueAtIndex(ff.getLogicalValueAtIndex(line,column)) == 0) { //get the value of the boat, which decrements by one everytime it is hit
                        System.out.println("And Sunk!");
                    }
                } else {
                    System.out.println("You hit water...");
                }
                ef.shot(line, column, ff);
                ef.displayBoard();
                if (ff.allShipsAreSunk()) {
                    System.out.println("YOU WON THE GAME!!\nGame over!");
                    gameOver = true; //ends the while loop
                }

            } else if (commandString.toUpperCase().equals("PRINT_SCORE") && firstParameter.isEmpty()) {
                //not implemented yet
            } else if (commandString.toUpperCase().equals("HELP") && firstParameter.isEmpty()) {
                System.out.println("List of commands:");
                System.out.println("- PRINT_LOCAL\n\t-> Prints your local board");
                System.out.println("- PRINT_REMOTE\n\t-> Prints your remote board");
                System.out.println("- SET_SHIP {shipIndex} {A...J} {0...9} {direction}\n\t-> Sets the ship of that index at precised coordinates and direction");
                System.out.println("\t-> Ship indexes are:\n\t\t- 0 for a CARRIER (5 wide)\n\t\t- 1 for a BATTLESHIP (4 wide)\n\t\t- 2 for a CRUISER (3 wide)\n\t\t- 3 for a SUBMARINE (3 wide)\n\t\t- 4 for a DESTROYER (2 wide)");
                System.out.println("\t-> Directions are: up, down, left, right");
                System.out.println("- REMOVE_SHIPS\n\t-> Removes all ships on the board");
                System.out.println("- SHOT {A...J} {1...10}\n\t-> Shoots at the precised coordinates");
                System.out.println("- PRINT_SCORE\n\t-> Prints yout current score");
                System.out.println("- EXIT\n\t-> Quits the game without saving");

                System.out.println("\nAll commands can be written with lower or upper case.");
            } else if (commandString.toUpperCase().equals("EXIT") && firstParameter.isEmpty()) { //quits the game
                System.out.println("Game over!");
                gameOver = true;
            } else {
                throw new InvalidCommandException();
            }

        } catch (IOException iox) { //should never happen
            System.out.println("Nothing was read");
        }
    }

    @Override
    public boolean getGameOver() {
        return gameOver;
    }

    
}
