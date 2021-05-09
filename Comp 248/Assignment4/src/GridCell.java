// -------------------------------------------------------
// Assignment 4
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This is essentially a POJO class that holds the attributes of a single cell within
the battleship game grid. Each cell in the BattleGrid array has an object of this
class in it.
*/
public class GridCell {
    //Contains the type of object that is contained in the cell. It can either be 
    //a ship, a grenade, or empty.
    private String cellContent;
    //Contains the owner of the cell object. It can either be the human player,
    //the computer player, or no one.
    private String cellOwner;
    //Indicates whether this cell has been called yet. True if called, false otherwise.
    private boolean cellCalled;
    
    //Default constructor for a grid cell. Creates an empty, uncalled GridCell.
    public GridCell() {
        cellContent = "";
        cellOwner = "";
        cellCalled = false;
    }
    
    //Getter method for the content of the cell.
    public String getCellContent() {
        return cellContent;
    }
    
    //Getter method for the owner of the cell.
    public String getCellOwner() {
        return cellOwner;
    }
    
    //Getter method for whether the cell has been called.
    public boolean isCellCalled() {
        return cellCalled;
    }
    
    //Setter method for the content and owner of the cell.
    public void setCellObject(String type, String owner) {
        this.cellContent = type;
        this.cellOwner = owner;
    }
    
    //Setter method for whether the cell has been called.
    public void setCellCalled(boolean cellCalled) {
        this.cellCalled = cellCalled;
    }
    
    //Method that returns the String representation of a cell within a grid. Output
    //is diffrent depending on the cell content and owner.
    @Override
    public String toString() {
        String output;
        switch(this.cellContent) {
            case "ship": output = "s"; break;
            case "gren": output = "g"; break;
            default: return "*";        
        }
        
        if(this.cellOwner.equals("comp"))
            return output.toUpperCase();
        return output;
    }
}