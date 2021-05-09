// -----------------------------------------------------
// Name and ID Kevin Marnet Scanlan 40175644
// COMP249
// Assignment # 1
// Due Date February 8, 2021
// ----------------------------------------------------

/**
 * Class for the individual tiles that make up the game board.
 * Mostly a POJO class.
 * @author Kevin Marnet Scanlan
 */
public class BoardTile {
    /**
     * The index of the tile compared to others on the board.
     * These are in ascending order on the game board.
     */
    private final int index;
    /**
     * The status (or content) of the tile. If a player lands on this tile, the
     * status becomes the player's name. If the tile contains the head of a snake
     * or the foot of a ladder, the status is the destination index of said snake
     * or ladder. Otherwise, the status is an empty string.
     */
    private String status = "";

    /**
     * BoardTile constructor that creates an empty BoardTile with the given index.
     * @param index The index of the tile.
     */
    public BoardTile(int index) {
        this.index = index;
    }

    /**
     * Getter Method for the tile index.
     * @return The tile index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter method for the tile status.
     * @return The tile status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for the tile status.
     * @param status The new tile status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * ToString method that formats the tile output to display nicely within the
     * visual representation of the board.
     * @return An abbreviated String representation of the tile status.
     */
    @Override
    public String toString() {
        if(status.isEmpty())
            return "";
        else if(Character.isDigit(status.charAt(0))) {
            if(Integer.parseInt(status) < index)
                return "S~";
            else
                return "L+";
        }
        else
            return status.substring(0,2);
    }
}