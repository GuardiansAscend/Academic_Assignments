// -------------------------------------------------------
// Assignment 4
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This program simulates a BattleShip game between the user and the computer, allowing
both the user and the computer to set up their ships and grenades and then giving
them each turns to rocket cells on the 8x8 game grid. When one of the players lose
all their ships, the game ends, all positions are displayed and the user is given
the option to either play again, or exit the program.

This is the driver class of my user vs. computer battleship game. It is responsible 
for running the gameplay loop (main method), and handling the gameplay logic. 
*/
import java.util.Scanner;
public class BattleShipDriver {
    //These are the string values of each type of cell information, used when
    //setting the cell values and retrieving rocketed cell information.
    private static final String SHIP_TYPE = "ship";
    private static final String GREANDE_TYPE = "gren";
    private static final String HUMAN_OWNER = "hum";
    private static final String COMPUTER_OWNER = "comp";
    private static final String CALLED_CELL = "call";
    //Indicates who's turn it currently is. True if it is the computer's turn,
    //false if it is the human's turn.
    private static boolean compTurn;
    //Activates when the current player hits a grenade. True when a grenade was
    //hit on the active turn, false otherwise.
    private static boolean grenaded;
    //Active if the player of the previous turn hit a grenade. True when a grenade
    //was hit on the previous turn, false otherwise.
    private static boolean grenadeBuffer;
    //The ship counters for both the computer and the human players. Decreases
    //everytime either of their respective ships are hit.
    private static int humShipCount = 6;
    private static int compShipCount = 6;
    
    //Main method that contains the gameplay loop.
    public static void main(String[] args) {
        //Creation of the scanner, and welcome message.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello and Welcome, to the Ultimate Battleship Game!");
        System.out.println("You will be facing me, the mighty computer!");
        do {
            System.out.print("Press \"y\" and then \"Enter\" when you are ready to start: ");
        } while(!scanner.next().equalsIgnoreCase("y"));
        System.out.println("OK, let's set up the game then.\n");
        System.out.println("Please note that all game coordinates are arranged into a grid of 8 columns (A-H) and 8 rows (1-8).");
        
        //The Gameplay loop. Everything that happens in here has to do with playing
        //the battleship game.
        while(true) {
            
            //Set-up phase for the human player. The player is prompted for every ship
            //and grenade they need to place. They are sent an error message and 
            //reprompted if they enter an invalid value.
            BattleGrid gameGrid = new BattleGrid();
            for (int i = 0; i < 10; i++) {
                if(i < 6){
                    System.out.print("Enter the coordinate for your ship #" + (i+1) + ": ");
                    String input = scanner.next();
                    if(!checkInput(input)){ 
                        i--; 
                        continue;
                    }
                    if(!gameGrid.setGridCell(input, SHIP_TYPE, HUMAN_OWNER).isEmpty()){
                        System.out.println(gameGrid.setGridCell(input, "", ""));
                        i--;
                    }
                }
                else{
                    System.out.print("Enter the coordinate for your grenade #" + (i-5) + ": ");
                    String input = scanner.next();
                    if(!checkInput(input)){ 
                        i--; 
                        continue;
                    }
                    if(!gameGrid.setGridCell(input, GREANDE_TYPE, HUMAN_OWNER).isEmpty()){
                        System.out.println(gameGrid.setGridCell(input, "", ""));
                        i--;
                    }
                }
            }
            
            //Set-up phase for the computer player. A random cell coordinate is
            //generated for every ship and grenade the computer owns. If the cell
            //is already occupied, they just generate another random coordinate.
            System.out.println("There you go, now all your ships and grenades are placed.");
            for (int i = 0; i < 10; i++) {
                int randRow = (int) (Math.random() * 8);
                int randCol = (int) (Math.random() * 8);
                if(i < 6) {
                    if(!gameGrid.setGridCell(gameGrid.getGrid()[randRow][randCol], SHIP_TYPE, COMPUTER_OWNER))
                        i--;
                }
                else {
                    if(!gameGrid.setGridCell(gameGrid.getGrid()[randRow][randCol], GREANDE_TYPE, COMPUTER_OWNER))
                        i--;
                }
            }
            System.out.println("I have also placed mine randomly across the grid. Let's Start!");
            
            //The attacking phase loop. Both the computer and human players' turns
            //are carried out in here, and the game changes is status after every turn.
            for (int i = 1; gameGrid.isMatchLive(); i++) {
                //The current game grid is printed at the start of every turn.
                System.out.println(gameGrid);
                //The turn number is displayed at the start of every turn.
                System.out.print("Turn #" + i + ": ");
                
                //The human player's turn. Takes an inputted cell coordinate from
                //the user and fires a rocket at that cell. The game then responds
                //according to the type of cell that was rocketed. Reprompts the user
                //if they give an invalid cell coordinate.
                if(!compTurn) {
                    System.out.print("Where will you shoot your rocket?: ");
                    String input = scanner.next();
                    if(!checkInput(input)){ 
                        i--; 
                        continue;
                    }
                    
                    String rocketResult = gameGrid.rocketCell(input);
                    if(rocketResult.length() > 8) {
                        System.out.println(rocketResult);
                        i--;
                        continue;
                    }
                    handleRocketHit(rocketResult);
                }
                
                //The computer player's turn. Generates a random cell coordinate,
                //displays the coordinate to the user and rockets it. The game 
                //then responds according to the type of cell that was rocketed.
                else {
                    int randCol = (int) (Math.random() * 8 + 1);
                    int randRow = (int) (Math.random() * 8 + 1);
                    String cellCoord = createColumnCoord(randCol) + randRow;
                    
                    System.out.println("I am shooting my rocket at cell " + cellCoord + ".");
                    handleRocketHit(gameGrid.rocketCell(cellCoord));
                }
                
                //The grenade hit logic. If only 1 player hits a grenade, the other
                //player is given 2 consecutive turns. If both players hit grenades
                //on consecutive turns, neither is given an extra turn.
                if(grenadeBuffer){
                    grenadeBuffer = false;
                    if(grenaded == grenadeBuffer)
                        compTurn = !compTurn;
                    grenaded = false;
                }
                if(grenaded) {
                    grenadeBuffer = true;
                    grenaded = false;
                }
                
                //Checks if one of the players is out of ships, and ends the game
                //if one of them has.
                if(humShipCount * compShipCount == 0)
                    gameGrid.setMatchLive(false);
                //Transfers the current turn to the opposing player.
                compTurn = !compTurn;
            }
            
            //Endgame messages. Displays the unhidden game grid and prompts the 
            //user to indicate whether they want a rematch.
            System.out.println("\nLet's reveal all the positions in the grid: ");
            System.out.println(gameGrid);
            System.out.println("That was pretty fun, I'm ready for a rematch!");
            System.out.print("If you want to play again, press \"y\" and then \"Enter\".\n" +
                    "Otherwise, press any other combination of keys followed by \"Enter\" to exit the program: ");
            
            //If the user didn't want a rematch, the program breaks from the game
            //loop. If they did, the game is reset and the loop restarts.
            if(!scanner.next().equalsIgnoreCase("y"))
                break;
            else{
                compTurn = false;
                humShipCount = 6;
                compShipCount = 6;
            }
        }
        
        //This is the closing message, which changes depending on who won the final game.
        if(humShipCount == 0)
            System.out.println("\nFarewell, challenger. Come back when you are ready to to give it another go. I'll be here.");
        else
            System.out.println("\nNot gonna give me a chance to redeem myself? OK then, I'll just practice on my own. Come back soon!");
    }
    
    //This method makes sure that the user enters a coordinate when prompted.
    public static boolean checkInput(String input) {
        if(!(input.length() == 2 && Character.isLetter(input.charAt(0)) && Character.isDigit(input.charAt(1))))
            System.out.println("That doesn't look like a cell coordinate, please try again.");
        return (input.length() == 2 && Character.isLetter(input.charAt(0)) && Character.isDigit(input.charAt(1)));
    }
    
    //This method turns the computer's randomly generated column number into the 
    //corresponding column letter, so it can be displayed to the user.
    public static String createColumnCoord(int index) {
        switch(index) {
            case 1: return "A";
            case 2: return "B";
            case 3: return "C";
            case 4: return "D";
            case 5: return "E";
            case 6: return "F";
            case 7: return "G";
            case 8: return "H";
            default: return "";
        }
    }
    
    //This method handles the data retrieved from the BattleGrid when a cell is
    //rocketed. It displays a different message for each outcome, and updates some
    //gameplay variables if needed.
    public static void handleRocketHit(String cellData) {
        if(cellData.equals(CALLED_CELL)) {
            if(compTurn)
                System.out.println("Oh no, my finger slipped and I hit an already called cell! I am so embarassed.");
            else
                System.out.println("Oh dear, that cell was alredy called! What a shame.");
        }
        else if(cellData.isEmpty()) {
            if(compTurn)
                System.out.println("Splash! It seems that I missed...");
            else
                System.out.println("Splash! You missed.");
        }
        
        else if(cellData.equals(GREANDE_TYPE + HUMAN_OWNER)) {
            if(compTurn)
                System.out.println("BOOM! Wow, that really blew up in my face. Good grenade placement.");
            else
                System.out.println("BOOM! ...you just set off your own grenade didn't you? Probably should have read the manual.");
            grenaded = true;
        }
        else if(cellData.equals(GREANDE_TYPE + COMPUTER_OWNER)) {
            if(compTurn)
                System.out.println("BOOM! Ah, there is where I placed my grenade. Too bad I pulled the pin...");
            else
                System.out.println("BOOM! You have walked right into my grenade trap! Get wrecked.");
            grenaded = true;
        }
        
        else if(cellData.equals(SHIP_TYPE + HUMAN_OWNER)) {
            if(compTurn)
                System.out.println("Ha Ha! I've downed one of your ships!");
            else
                System.out.println("You seem to have blown up your own ship in your confusion...are you alright?");
            System.out.println("You have " + --humShipCount + " ship(s) remaining.");
            
            if(humShipCount == 0) 
                System.out.println("GAME OVER. The mighty Computer triumphs again!");
        }
        else if(cellData.equals(SHIP_TYPE + COMPUTER_OWNER)) {
            if(compTurn)
                System.out.println("Ha Ha! ...hold on, that was my ship. That's not good.");
            else
                System.out.println("Score! You have got one of my ships, well done!");
            System.out.println("I have " + --compShipCount + " ship(s) remaining.");
            
            if(compShipCount == 0)
                System.out.println("I can't believe it, you won! I guess you're better than I thought...");
        }
    }
}