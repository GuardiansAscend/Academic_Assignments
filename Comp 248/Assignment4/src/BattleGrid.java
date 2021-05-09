// -------------------------------------------------------
// Assignment 4
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This is the class that handles the battleship game grid. It constructs the gamegrid,
and handles the interaction between the driver class' gameplay logic and the
individual game grid cells.
*/
public class BattleGrid {
    //Contains the 2D array of cells that make up the game grid.
    private GridCell[][] grid;
    //Indicates whether the game is still live (no one has won). True if the game
    //is live, false if the game has ended.
    private boolean matchLive;
    
    //Default BattleGrid constructor. Creates a new 8x8 game grid, and makes the
    //match live.
    public BattleGrid() {
        grid = new GridCell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new GridCell();
            }
        }
        matchLive = true;
    }
    
    //This method is used to convert String coordinate inputs from the user and
    //computer into a cell index for the array.
    private int convertCoord(char coordinate) {
        switch(coordinate) {
            case 'A': case 'a': case '1': return 0;
            case 'B': case 'b': case '2': return 1;
            case 'C': case 'c': case '3': return 2;
            case 'D': case 'd': case '4': return 3;
            case 'E': case 'e': case '5': return 4;
            case 'F': case 'f': case '6': return 5;
            case 'G': case 'g': case '7': return 6;
            case 'H': case 'h': case '8': return 7;
            default: return -1;
        }
    }
    
    //This is a setter method for a Grid cell, which is used for the human player
    //because it takes the cell coordinate as a String. Returns an error message 
    //if given an invalid cell cooridnate.
    public String setGridCell(String cell, String type, String owner) {
        if(convertCoord(cell.charAt(0)) == -1 || convertCoord(cell.charAt(1)) == -1) 
            return "That cell coordinate is invalid, please enter in the correct range.";
        
        GridCell gridCellObj = grid[convertCoord(cell.charAt(0))][convertCoord(cell.charAt(1))];
        if(!gridCellObj.getCellContent().isEmpty())
            return "You already put soemthing in that cell, try another one.";
        
        gridCellObj.setCellObject(type, owner);
        return "";
    }
    
    //This is a setter method for a Grid cell, which is used for the computer player
    //because it takes the cell coordinate as a GridCell object. Returns false if
    //an occupied cell is chosen, and true otherwise.
    public boolean setGridCell(GridCell cell, String type, String owner) {
        if(!cell.getCellContent().isEmpty())
            return false;
        
        cell.setCellObject(type, owner);
        return true;
    }
    
    //This method reacts to a cell being rocketed, and returns what type of cell
    //has been called. It transforms a uncalled cell into a called one if need be,
    //and returns an error message for an incorrect cell coordinate.
    public String rocketCell(String cell) {
        if(convertCoord(cell.charAt(0)) == -1 || convertCoord(cell.charAt(1)) == -1)
            return "That cell coordinate is invalid, please enter in the correct range.";
        
        GridCell gridCellObj = grid[convertCoord(cell.charAt(0))][convertCoord(cell.charAt(1))];
        if(gridCellObj.isCellCalled()) 
            return "call";
        gridCellObj.setCellCalled(true);
        
        return gridCellObj.getCellContent() + gridCellObj.getCellOwner();
    }
    
    //This method creates a String visual representation of the BattleGrid, along
    //with the individual grid cells inside the BattleGrid. It also hides the content 
    //of uncalled cells, unless the game is no longer live.
    @Override
    public String toString() {
        String output = "\n  A B C D E F G H\n";
        for (int i = 0; i < 10; i++) {
            if(i == 0 || i == 9)
                    output += "  - - - - - - - -";
            
            else {
                output += i;
                for (int j = 0; j < 17; j++) {
                    if(j % 2 == 0)
                        output += "|";
                    else if(!grid[j/2][i-1].isCellCalled() && matchLive)
                        output += "_";
                    else 
                        output += grid[j/2][i-1];
                }
            }
            
            output += "\n";
        }
        return output;
    }
    
    //Getter method for the 2D array of GridCells.
      public GridCell[][] getGrid() {
        return grid;
    }
    
    //Getter method for whether the game is live or not.
    public boolean isMatchLive() {
        return matchLive;
    }
    
    //Setter method for whether the game is live or not.
    public void setMatchLive(boolean matchLive) {
        this.matchLive = matchLive;
    }
}