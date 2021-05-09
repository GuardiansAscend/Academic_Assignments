import java.util.Scanner;
// -----------------------------------------------------
// Name and ID Kevin Marnet Scanlan 40175644
// COMP249
// Assignment # 1
// Due Date February 8, 2021
// ----------------------------------------------------

/**
 * Class that contains the game's board and the main gameplay functions.
 * Also handles most interactions between the tiles and the driver.
 * @author Kevin Marnet Scanlan
 */
public class LadderandSnake {
    /**
     * The board that the game is played on.
     */
    private final BoardTile [][] gameBoard =  new BoardTile[10][10];

    /**
     * Constructor that creates the game, fills up the game board and places all
     * the snakes and ladders. See the assignment figure for the basis of the 
     * positioning of the snakes and ladders.
     */
    public LadderandSnake() {
        for(int i = 0; i < gameBoard.length; i++) {
            for (int j = 1; j <= gameBoard[i].length; j++) {
                gameBoard[i][j-1] = new BoardTile(10*i + j);
            }
        }
        
        //Placement of snakes and ladders
        gameBoard[0][0].setStatus("38");
        gameBoard[0][3].setStatus("14");
        gameBoard[0][8].setStatus("31");
        gameBoard[1][5].setStatus("6");
        gameBoard[2][0].setStatus("42");
        gameBoard[2][7].setStatus("84");
        gameBoard[3][5].setStatus("44");
        gameBoard[4][7].setStatus("30");
        gameBoard[5][0].setStatus("67");
        gameBoard[7][0].setStatus("91");
        gameBoard[7][8].setStatus("19");
        gameBoard[7][9].setStatus("100");
        gameBoard[9][2].setStatus("68");
        gameBoard[9][4].setStatus("24");
        gameBoard[9][6].setStatus("76");
        gameBoard[9][7].setStatus("78");
    }

    /**
     * Getter for the game board. This method is unused in the current implementation
     * of the program.
     * @return The game board.
     */
    private BoardTile[][] getGameBoard() {
        return gameBoard;
    }
    
    /**
     * Finds the BoardTile that has a given index on the board. If it could not 
     * find said index, returns null.
     * @param index The index of the BoardTile we are searching for.
     * @return The BoardTile at the specified index.
     */
    public BoardTile getTileAt(int index) {
        for(BoardTile[] boardTiles: gameBoard){
            for (BoardTile boardTile : boardTiles) {
                if(index == boardTile.getIndex())
                    return boardTile;
            }
        }
        return null;
    }
    
    /**
     * Finds the BoardTile that has a given status on the board. Used to find the
     * BoardTile where an individual player is located.
     * @param player The status (name) of the player we are looking for.
     * @return The BoardTile with the specified status.
     */
    public BoardTile getTileWith(String player) {
        for(BoardTile[] boardTiles: gameBoard){
            for (BoardTile boardTile : boardTiles) {
                if(player.equals(boardTile.getStatus()))
                    return boardTile;
            }
        }
        return null;
    }
    
    /**
     * Method that flips a 6-sided dice, and returns the result.
     * @return The result of the dice flip.
     */
    public static int flipDice(){
        return (int) (Math.random() * 6 + 1);
    }
    
    /**
     * Method that runs the gameplay loop for a snakes and ladders game. The array
     * of players is sorted in the driver, and passed here for the gameplay.
     * @param players An array of players sorted in turn order.
     */
    public void play(String[] players) {
        //Index of the player who's turn it is
        int currentTurn = 0;
        //The 100th tile, or the winning tile
        BoardTile winningSquare = this.getTileAt(100);
        Scanner scanner =  new Scanner(System.in);
        
        //gameplay loop
        while(true){
            int diceResult = flipDice();
            BoardTile currentTile = this.getTileWith(players[currentTurn]);
            System.out.println(players[currentTurn] + " rolls a " + diceResult);
            
            //the first turn, starting at figurative tile "0"
            if(currentTile == null)
                this.handleMove(diceResult, players[currentTurn]);
            //all subsequent turns
            else {
                currentTile.setStatus("");
                this.handleMove(currentTile.getIndex() + diceResult, players[currentTurn]);
            }
            
            //Check if a player won the game
            if(!winningSquare.getStatus().isEmpty())
                break;
            
            //Prompt to to continue the game or display the Board in the console
            System.out.println("Press \"p\" to pass onto the next player's turn, " +
                        "or anything else to display the board.");
            while(!scanner.next().equals("p")) {
                System.out.println(this);
                System.out.println("Press \"p\" to pass onto the next player's turn, " +
                        "or anything else to display the board.");
            }
            
            //update for the player turn
            if(currentTurn == players.length - 1)
                currentTurn = 0;
            else
                currentTurn++;
        }
        
        System.out.println("FINISHED! We have a winner, and their name is " + winningSquare.getStatus() + 
                "! Congratulations!");
    }
    
    /**
     * Method that moves a given player's position on the board, and handles the
     * result of the player's movement. Exclusively used by the play() method.
     * @param destination The index the player is being moved to.
     * @param player The player that is moving.
     */
    private void handleMove(int destination, String player) {
        BoardTile tile = this.getTileAt(destination);
        //If the player has overshot tile 100
        if(tile == null) {
            System.out.println(player + " has rolled too high, and bounces off the last square");
            handleMove(200 - destination, player);
        }
        //If the square is a valid square to end on
        else if(tile.getStatus().isEmpty()) {
            tile.setStatus(player);
            System.out.println(player + " has ended up on tile " + destination);
        }
        //If the square is a snake or a ladder
        else if(Character.isDigit(tile.getStatus().charAt(0))) {
            if(Integer.parseInt(tile.getStatus()) >  destination)
                System.out.println(player + " climbs the ladder on tile " + destination);
            else
                System.out.println(player + " slides down the snake on tile " + destination);
            handleMove(Integer.parseInt(tile.getStatus()), player);
        }
        //If the square is already occupied
        else {
            System.out.println(tile.getStatus() + " is already on tile " + destination +
                    ", " + player + " moves past them");
            handleMove(destination + 1, player);
        }
    }
    
    /**
     * toString method that creates a visual screen representation of the entire
     * board, and all the individual tiles. Uses the status of the BoardTiles to
     * locate each player.
     * @return The String visualization of the game board.
     */
    @Override
    public String toString() {
        String output = "\n                             Snakes & Ladders\n";
        
        for (int i = 30; i >= 0; i--) {
            for (int j = 0; j <= 20; j++) {
                if(i%3 == 0)
                    output += (j%2 == 0) ? "+" : "------";
                
                else {
                    //The equations used here are hand-calculated relationships 
                    //between the loop index and the tiles
                    if (j%2 == 0)
                        output += "|";
                    else if (i%3 == 2) {
                        if(i%2 == 0)
                            output += String.format("%-6d", ((i-2)/3) * 10 + j/2 + 1);
                        else
                            output += String.format("%-6d", (i-(i/6))*4 - j/2);
                    }
                    else {
                        if(i%2 == 0)
                            output += String.format("    %-2s", this.getTileAt((i-(i/6) + 1)*4 - j/2));
                        else
                            output += String.format("    %-2s", this.getTileAt(((i-1)/3)*10 + j/2 + 1));
                    }
                }
            }
            output += "\n";
        }
        
        return output;
    }
}
