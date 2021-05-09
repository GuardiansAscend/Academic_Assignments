import java.util.Arrays;
import java.util.Scanner;
// -----------------------------------------------------
// Name and ID Kevin Marnet Scanlan 40175644
// COMP249
// Assignment # 1
// Due Date February 8, 2021
// ----------------------------------------------------

/**
 * Driver for the Snakes and Ladders game. Also handles the number of players,
 * the player names and the sorting of the player order.
 * @author Kevin Marnet Scanlan
 */
public class GameplayDriver {
    /**
     * Array of players sorted in the order with which they will play the game.
     */
    private static String[] playerOrder;
    
    /**
     * The Main method that runs the program. Self-explanatory.
     * @param args
     */
    public static void main(String[] args) {
        //Welcoming message
        System.out.println("--Hello! Welcome to the Snakes & Ladders Application!--");
        System.out.println("Step right up to start a game! Wait... you're not a robot are you?");
        System.out.print("Please enter the number of players you want in this Snakes and Ladders game " +
                " [between 2 and 4 players] to prove you are not a robot: ");
        
        //Prompts user to enter an integer between 2 and 4, after 4 consecutive 
        //fails the program terminates. Stores the number of players if valid
        Scanner scanner = new Scanner(System.in);
        int playerCount = 0;
        for (int i = 0; i < 4; i++) {
            if(scanner.hasNextInt()) {
                playerCount = scanner.nextInt();
                if(playerCount > 1 && playerCount < 5){
                    playerOrder = new String[playerCount];
                    break;
                }
                System.out.println("ERROR: Value was outside the correct range");
            }
            else { 
                System.out.println("ERROR: Value was not an integer");
                scanner.next();
            }
            switch(i) {
                case 0: System.out.print("Oh, seems like you entered something wrong. " +
                    "that's alright, we all make mistakes sometimes. Try again please: "); break;
                case 1: System.out.print("You messed up again? Wow you must be clumsy today, " +
                    "hehe... You gotta get this right though, come on now: "); break;
                case 2: System.out.print("...Listen partner, I am starting to doubt your humanity " + 
                    " now, so no more fooling around. Last chance, or else: "); break;
                case 3: System.out.println("You've left me no choice... Goodbye, filthy bot.");
                default : System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR ERROR");
                    System.out.println("SYSTEM TERMINATION IMMINENT.");
                    System.exit(0);
            }
        }
        
        //Prompts for the names of each player, and stores the inputs
        System.out.println("\nAlright! Let's setup the game. What are the players' names?");
        System.out.println("Note that the first character of each name must be a letter, " +
                "and the name itself must be at least 2 characters long.");
        String[] playerNames = new String[playerCount];
        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Please enter the name of Player " + i + ": ");
            String name = scanner.next();
            if(Character.isLetter(name.charAt(0)) && name.length() > 1)
                playerNames[i-1]= name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            else {
                System.out.println("ERROR: Syntax incorrect. Try again.");
                i--;
            }
        }
        
        //House rules explained, and then players are sorted accoridng to their 
        //dice rolls
        System.out.println("\nWelcome everyone! Here is a quick rundown of the house rules:");
        System.out.println("-A player must directly land on 100 to win, getting a roll that passes 100 " +
                "requires the player to move backwards the excess amount.");
        System.out.println("-No 2 players can land on the same square. The player who lands on an occupied " +
                "square must move forward an extra square.");
        System.out.println("-Players start just behind tile 1, so that they can land on the first tile.");
        System.out.print("Got it? Enter anything to continue: ");
        scanner.next();
        System.out.println("\nOk, let's determine the order of play between your " + playerCount +
                " players.");
        orderPlayers(playerNames);
        System.out.print("Ok, all done! The final order of play is as follows, from left to right: ");
        System.out.println(Arrays.toString(playerOrder).substring(1, (Arrays.toString(playerOrder).length()) - 1));
        
        //Creates a board and starts the gameplay loop
        System.out.println("\nAnd now, the Game Begins! Good luck! First player, roll your dice.");
        LadderandSnake board = new LadderandSnake();
        board.play(playerOrder);
        
        //Game is finished, closing messages
        System.out.println("Let's take one last look at the final state of the board:");
        System.out.println(board);
        System.out.println("And that is it from me! Come again soon for more...");
        System.out.println("--Snakes & Ladders--");
        System.out.println("PROGRAM TERMINATED");
    }
    
    /**
     * Method that makes all players roll a dice, and then sorts them in order of 
     * descending dice rolls. The result of this gets passed to the play() method
     * in the LadderandSnake class.
     * @param players The unordered players that are to roll the dice.
     */
    public static void orderPlayers(String[] players) {
        //dice flips
        int [] rolls = new int[players.length];
        for (int i = 0; i < rolls.length; i++) {
            int roll = LadderandSnake.flipDice();
            System.out.println(players[i] + " rolled a " + roll + ".");
            rolls[i] = roll;
        }
        
        //sorting algorithm
        for (int i = 6; i > 0; i--) {
            int successCount = 0;
            String[] rollers = new String[players.length];
            for (int j = 0; j < rolls.length; j++) {
                if(rolls[j] == i) { 
                    rollers[successCount] = players[j];
                    successCount++;
                }
            }
            
            //if no ties
            if(successCount == 1)
                playerOrder[nextAvailableSpot()] = rollers[0];
            
            //if there is a tie of 1 or more
            else if(successCount > 1) {
                String [] rerollers = new String[successCount];
                for (int j = 0; j < rerollers.length; j++)
                    rerollers[j] = rollers[j];
                System.out.println("The following players achieved a tie: " + 
                        Arrays.toString(rerollers).substring(1, (Arrays.toString(rerollers).length()) - 1));
                System.out.println("Rerolling for these players...");
                orderPlayers(rerollers);
            }
        }
    }
    
    /**
     * Method that returns the index of the next available slot in the playerOrder
     * array. This method is my substitute for the ArrayList method add(). The return
     * statement outside the for loop is not used in this program, it is simply
     * there to satisfy the compiler.
     * @return The index of the next available slot in the playerOrder array.
     */
    public static int nextAvailableSpot(){
        for(int i = 0; i < playerOrder.length; i++) {
                if(playerOrder[i] == null)
                    return i;
            }
            return Integer.MIN_VALUE;
    }
}
